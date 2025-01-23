package org.example.repository;

import org.example.model.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation, UUID> {

    @Query(value = "select calculate_wallet_balance(:walletId)", nativeQuery = true)
    BigDecimal calculateWalletBalance(UUID walletId);

    @Query("select operation from Operation operation where operation.wallet.id = :walletId order by created")
    Page<Operation> findOperations(UUID walletId, Pageable pageable);

    @Query("select operation from Operation operation where operation.id = :id")
    Optional<Operation> findById(UUID id);
}
