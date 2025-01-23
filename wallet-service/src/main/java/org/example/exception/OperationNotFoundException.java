package org.example.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class OperationNotFoundException extends WalletException {

    private static final String EXCEPTION_CODE = "001";
    private static final String EXCEPTION_TITLE = "Operation not found!";
    private static final String EXCEPTION_DETAIL = "Operation with id: %s not found!";

    public OperationNotFoundException(UUID id) {
        super(EXCEPTION_CODE, EXCEPTION_TITLE, String.format(EXCEPTION_DETAIL, id), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
