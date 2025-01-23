package org.example;

import org.example.api.dto.WalletDto;
import org.example.model.Operation;
import org.example.model.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = OperationMapper.class)
public interface WalletMapper {

    @Mapping(target = "id", source = "wallet.id")
    @Mapping(target = "balance", source = "wallet.balance")
    @Mapping(target = "operations", source = "operations")
    @Mapping(target = "created", source = "wallet.created")
    WalletDto mapToDto(Wallet wallet, Page<Operation> operations);
}
