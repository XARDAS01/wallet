package org.example.resource;

import org.example.annotation.IT;
import org.example.api.dto.ReferenceDto;
import org.example.api.dto.WalletDto;
import org.example.enums.OperationStatus;
import org.example.enums.OperationType;
import org.example.model.Operation;
import org.example.model.Wallet;
import org.example.repository.OperationRepository;
import org.example.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IT
class WalletResourceIT {

    private static final ParameterizedTypeReference<ResponseEntity<WalletDto>> TYPE = new ParameterizedTypeReference<>() {};

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private TestRestTemplate restTemplate;

    private UUID walletId;
    private UUID operationId;

    @BeforeEach
    public void init() {
        final var wallet = transactionTemplate.execute(s ->
                walletRepository.save(new Wallet()));
        walletId = wallet.getId();

        operationId = transactionTemplate.execute(s -> {
            final var next = new Operation();
            next.setWallet(wallet);
            next.setAmount(BigDecimal.valueOf(100));
            next.setOperationType(OperationType.DEPOSIT);
            next.setOperationStatus(OperationStatus.APPROVED);
            return operationRepository.save(next).getId();
        });
    }

    @Test
    public void createWallet_happy() {
        transactionTemplate.executeWithoutResult(s ->
                assertEquals(1, walletRepository.findAll().size()));

        final var response = restTemplate.postForEntity(
                "/api/v1/wallet/create",
                Void.class,
                ReferenceDto.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        transactionTemplate.executeWithoutResult(s ->
                assertEquals(2, walletRepository.findAll().size()));
    }

    @Test
    public void getWalletById_happy() {
        transactionTemplate.executeWithoutResult(s ->
                assertNotNull(walletRepository.findById(walletId)));

        final var response = restTemplate.exchange(
                "/api/v1/wallets/" + walletId,
                HttpMethod.GET,
                null,
                WalletDto.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        transactionTemplate.executeWithoutResult(s ->
                assertEquals(walletId, response.getBody().getId()));
    }
}
