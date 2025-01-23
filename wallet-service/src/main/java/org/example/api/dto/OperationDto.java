package org.example.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.example.enums.OperationStatus;
import org.example.enums.OperationType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Schema(description = "Data Transfer Object for an operation")
public record OperationDto(
        @NotNull(message = "Operation ID cannot be null")
        @Schema(
                description = "Unique identifier for the operation",
                example = "123e4567-e89b-12d3-a456-426614174000"
        )
        UUID id,

        @NotNull(message = "Operation type cannot be null")
        @Schema(
                description = "Type of the operation, such as DEPOSIT or WITHDRAWAL",
                example = "DEPOSIT"
        )
        OperationType operationType,

        @NotNull(message = "Operation status cannot be null")
        @Schema(
                description = "Type of the operation, such as APPROVED or CANCELLED",
                example = "APPROVED"
        )
        OperationStatus operationStatus,

        @NotNull(message = "")
        @Schema()
        BigDecimal amount,

        @NotNull(message = "Creation timestamp cannot be null")
        @Schema(
                description = "Timestamp when the operation was created",
                example = "2023-03-15T10:15:30Z"
        )
        Instant created
) {
}
