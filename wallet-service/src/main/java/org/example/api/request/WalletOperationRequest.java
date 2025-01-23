package org.example.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.enums.OperationType;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Request for creating an operation")
public record WalletOperationRequest(

        @NotNull(message = "Wallet ID cannot be null")
        @Schema(
                description = "Unique identifier for the user's wallet",
                example = "123e4567-e89b-12d3-a456-426614174000"
        )
        UUID walletId,

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be positive")
        @Schema(description = "Amount involved in the operation", example = "100.50")
        BigDecimal amount,

        @NotNull(message = "Operation type cannot be null")
        @Schema(description = "Type of the operation", example = "DEPOSIT")
        OperationType operationType
) {
}
