package org.example.mapper;

import org.example.OperationMapperImpl;
import org.example.api.request.WalletOperationRequest;
import org.example.enums.OperationStatus;
import org.example.factory.OperationMapperFactory;
import org.example.model.Operation;
import org.example.model.Wallet;
import org.instancio.junit.InstancioSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OperationMapperTest {

    @Mock
    private OperationMapperFactory operationMapperFactory;

    @InjectMocks
    private OperationMapperImpl operationMapper;

    @ParameterizedTest
    @InstancioSource
    public void mapToEntity_happy(
            Wallet wallet,
            WalletOperationRequest walletOperationRequest,
            OperationStatus operationStatus
    ) {
        final var operation = new Operation();
        operation.setWallet(wallet);
        operation.setAmount(walletOperationRequest.amount());
        operation.setOperationType(walletOperationRequest.operationType());

        Mockito.when(operationMapperFactory.buildOperation(wallet, walletOperationRequest)).thenReturn(operation);
        final var result = operationMapper.mapToEntity(wallet, walletOperationRequest, operationStatus);

        assertEquals(walletOperationRequest.amount(), result.getAmount());
        assertEquals(walletOperationRequest.operationType(), result.getOperationType());
        assertEquals(operationStatus, result.getOperationStatus());
    }

    @ParameterizedTest
    @InstancioSource
    public void mapToDto_happy(Operation source) {
        final var result = operationMapper.mapToDto(source);

        assertEquals(source.getAmount(), result.amount());
        assertEquals(source.getId(), result.id());
        assertEquals(source.getOperationStatus(), result.operationStatus());
        assertEquals(source.getOperationType(), result.operationType());
        assertEquals(source.getCreated(), result.created());
    }
}
