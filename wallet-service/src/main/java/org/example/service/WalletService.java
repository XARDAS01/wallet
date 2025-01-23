package org.example.service;

import org.example.api.dto.WalletDto;
import org.example.api.request.WalletOperationRequest;
import org.example.jpa.domain.DbId;
import org.springframework.lang.NonNull;

import java.util.UUID;

public interface WalletService {

    @NonNull
    DbId<UUID> create();

    @NonNull
    WalletDto getById(@NonNull UUID id, int page, int size);

    void delete(@NonNull UUID id);

    void operation(@NonNull WalletOperationRequest walletOperationRequest);
}
