package org.example.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.example.jpa.domain.DbId;
import org.hibernate.annotations.CreationTimestamp;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "wallets")
public class Wallet implements DbId<UUID> {

    @Id
    @GeneratedValue
    private UUID id;

    @Transient
    private BigDecimal balance;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Operation> operations;

    @CreationTimestamp
    private Instant created;
}
