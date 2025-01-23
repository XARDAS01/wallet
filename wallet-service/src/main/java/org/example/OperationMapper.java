package org.example;

import org.example.api.dto.OperationDto;
import org.example.api.request.WalletOperationRequest;
import org.example.enums.OperationStatus;
import org.example.factory.OperationMapperFactory;
import org.example.model.Operation;
import org.example.model.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = "spring", uses = OperationMapperFactory.class)
public interface OperationMapper {

    @Mapping(target = "id", ignore = true)
    Operation mapToEntity(Wallet wallet, WalletOperationRequest walletOperationRequest, OperationStatus operationStatus);

    OperationDto mapToDto(Operation source);

    default Page<OperationDto> mapToDto(Page<Operation> source) {
        return new PageImpl<OperationDto>(
                source.getContent().stream()
                        .map(this::mapToDto)
                        .toList(),
                source.getPageable(),
                source.getTotalElements());
    }
}
