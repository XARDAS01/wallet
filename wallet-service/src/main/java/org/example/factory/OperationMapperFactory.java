package org.example.factory;

import org.example.api.request.WalletOperationRequest;
import org.example.model.Operation;
import org.example.model.Wallet;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
public record OperationMapperFactory() {

    @ObjectFactory
    public Operation buildOperation(Wallet wallet, WalletOperationRequest operationCreateRequest) {
        final var base = getBase();

        base.setWallet(wallet);
        base.setAmount(operationCreateRequest.amount());
        base.setOperationType(operationCreateRequest.operationType());

        return base;
    }

    private Operation getBase() {
        return new Operation();
    }
}
