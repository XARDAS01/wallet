package org.example.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Schema(description = "Data Transfer Object for a wallet")
public class WalletDto {

    @Schema(
            description = "Unique identifier for the wallet",
            example = "123e4567-e89b-12d3-a456-426614174000"
    )
    private UUID id;

    @Schema(description = "Current balance of the wallet", example = "1000.00")
    private BigDecimal balance;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Page of operations associated with the wallet")
    private Page<OperationDto> operations;

    @Schema(
            description = "Timestamp when the wallet was created",
            example = "2023-01-21T12:34:56Z"
    )
    private Instant created;
}
