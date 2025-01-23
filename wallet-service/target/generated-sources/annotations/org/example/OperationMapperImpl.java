package org.example;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.example.api.dto.OperationDto;
import org.example.api.request.WalletOperationRequest;
import org.example.enums.OperationStatus;
import org.example.enums.OperationType;
import org.example.factory.OperationMapperFactory;
import org.example.model.Operation;
import org.example.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-23T19:00:25+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.8 (Amazon.com Inc.)"
)
@Component
public class OperationMapperImpl implements OperationMapper {

    @Autowired
    private OperationMapperFactory operationMapperFactory;

    @Override
    public Operation mapToEntity(Wallet wallet, WalletOperationRequest walletOperationRequest, OperationStatus operationStatus) {
        if ( wallet == null && walletOperationRequest == null && operationStatus == null ) {
            return null;
        }

        Operation operation = operationMapperFactory.buildOperation( wallet, walletOperationRequest );

        if ( wallet != null ) {
            operation.setCreated( wallet.getCreated() );
        }
        if ( walletOperationRequest != null ) {
            operation.setOperationType( walletOperationRequest.operationType() );
            operation.setAmount( walletOperationRequest.amount() );
        }
        operation.setOperationStatus( operationStatus );

        return operation;
    }

    @Override
    public OperationDto mapToDto(Operation source) {
        if ( source == null ) {
            return null;
        }

        UUID id = null;
        OperationType operationType = null;
        OperationStatus operationStatus = null;
        BigDecimal amount = null;
        Instant created = null;

        id = source.getId();
        operationType = source.getOperationType();
        operationStatus = source.getOperationStatus();
        amount = source.getAmount();
        created = source.getCreated();

        OperationDto operationDto = new OperationDto( id, operationType, operationStatus, amount, created );

        return operationDto;
    }
}
