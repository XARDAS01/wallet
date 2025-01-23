package org.example.mapper;

import org.example.OperationMapper;
import org.example.WalletMapperImpl;
import org.example.model.Operation;
import org.example.model.Wallet;
import org.instancio.Instancio;
import org.instancio.junit.InstancioSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class WalletMapperTest {

    @Mock
    private OperationMapper operationMapper;

    @InjectMocks
    private WalletMapperImpl walletMapper;

    @ParameterizedTest
    @InstancioSource
    public void mapToDto_happy(Wallet wallet) {
        final var operationList = Instancio.createList(Operation.class);
        final var operations = new PageImpl<>(
                operationList, Pageable.ofSize(10), operationList.size());

        Mockito.when(operationMapper.mapToDto(any(PageImpl.class)))
                .thenReturn(new PageImpl(List.of(), Pageable.ofSize(10), operations.getTotalElements()));

        final var result = walletMapper.mapToDto(wallet, operations);

        assertEquals(wallet.getId(), result.getId());
        assertEquals(wallet.getBalance(), result.getBalance());
        assertEquals(operations.getTotalElements(), result.getOperations().getTotalElements());
        assertEquals(wallet.getCreated(), result.getCreated());
    }
}
