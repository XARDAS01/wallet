package org.example.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.api.dto.WalletDto;
import org.example.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallets")
record WalletsResource(WalletService walletService) {

    @GetMapping("/{wallet_uuid}")
    @Operation(summary = "${wallet.getById}", responses = @ApiResponse(responseCode = "200"))
    public ResponseEntity<WalletDto> getWalletById(
            @PathVariable("wallet_uuid") UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok().body(walletService.getById(id, page, size));
    }
}
