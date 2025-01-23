package org.example.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.OperationStatus;
import org.example.enums.OperationType;
import org.example.jpa.domain.DbId;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "operations")
public class Operation implements DbId<UUID> {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(value = EnumType.STRING)
    private OperationType operationType;
    @Enumerated(value = EnumType.STRING)
    private OperationStatus operationStatus;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @CreationTimestamp
    private Instant created;
}
