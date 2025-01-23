package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.api.dto.WalletDto;
import org.example.api.request.WalletOperationRequest;
import org.example.enums.OperationStatus;
import org.example.enums.OperationType;
import org.example.exception.OperationMultipleRetriesException;
import org.example.exception.OperationWithdrawException;
import org.example.exception.WalletNotFoundException;
import org.example.jpa.domain.DbId;
import org.example.OperationMapper;
import org.example.WalletMapper;
import org.example.model.Wallet;
import org.example.repository.OperationRepository;
import org.example.repository.WalletRepository;
import org.example.service.WalletService;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    @Override
    @NonNull
    public DbId<UUID> create() {
        return walletRepository.save(new Wallet());
    }

    @Override
    @NonNull
    @Transactional(readOnly = true)
    public WalletDto getById(@NonNull UUID id, int page, int size) {
        return walletRepository
                .findById(id)
                .map(wallet -> {
                    wallet.setBalance(operationRepository.calculateWalletBalance(id));
                    return walletMapper.mapToDto(wallet, operationRepository.findOperations(id, PageRequest.of(page, size)));
                })
                .orElseThrow(() -> new WalletNotFoundException(id));
    }

    @Override
    public void delete(@NonNull UUID id) {
        try {
            walletRepository.deleteById(id);
            log.info("Wallet with ID {} deleted.", id);
        } catch (EmptyResultDataAccessException ex) {
            log.error("Wallet with ID {} not found for deletion.", id);
            throw new WalletNotFoundException(id);
        }
    }

    @Override
    @Retryable(retryFor = DataAccessException.class, maxAttempts = 15, backoff = @Backoff(delay = 1000))
    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = OperationWithdrawException.class)
    public void operation(@NonNull WalletOperationRequest walletOperationRequest) {
        walletRepository
                .findById(walletOperationRequest.walletId())
                .ifPresentOrElse(
                        wallet -> {
                            final var balance = operationRepository.calculateWalletBalance(wallet.getId());
                            if (walletOperationRequest.operationType() == OperationType.WITHDRAW &&
                                    balance.compareTo(walletOperationRequest.amount()) < 0) {
                                operationRepository.save(operationMapper.mapToEntity(wallet, walletOperationRequest, OperationStatus.CANCELLED));
                                log.info("Operation cancelled due low balance.");
                                throw new OperationWithdrawException(walletOperationRequest.walletId());
                            }
                            operationRepository.save(operationMapper.mapToEntity(wallet, walletOperationRequest, OperationStatus.APPROVED));
                            log.info("Operation approved with amount: {}.", walletOperationRequest.amount());
                        },
                        () -> {
                            throw new WalletNotFoundException(walletOperationRequest.walletId());
                        });
    }

    @Recover
    public void recover(DataAccessException e, WalletOperationRequest walletOperationRequest) {
        log.error("Operation with amount: {} for walletId: {} failed after multiple retries.",
                walletOperationRequest.amount(), walletOperationRequest.walletId());
        throw new OperationMultipleRetriesException(
                walletOperationRequest.amount(), walletOperationRequest.walletId());
    }
}
