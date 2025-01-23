package org.example.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.example.api.dto.ReferenceDto;
import org.example.api.request.WalletOperationRequest;
import org.example.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallet")
record WalletResource(WalletService walletService) {

    @PostMapping("/create")
    @Operation(summary = "${wallet.create}", responses = @ApiResponse(responseCode = "201"))
    public ResponseEntity<ReferenceDto> createWallet() {
        final var next = walletService.create();
        return ResponseEntity.created(URI.create("http://localhost:8081/api/v1/wallets/" + next.getId()))
                .body(ReferenceDto.of(next.getId()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "${wallet.deleteById}", responses = @ApiResponse(responseCode = "204"))
    public ResponseEntity<Void> deleteWallet(@PathVariable UUID id) {
        walletService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "${wallet.operation}", responses = @ApiResponse(responseCode = "200"))
    public ResponseEntity<ReferenceDto> walletOperation(@RequestBody @Valid WalletOperationRequest walletOperationRequest) {
        walletService.operation(walletOperationRequest);
        return ResponseEntity.noContent().build();
    }
}
