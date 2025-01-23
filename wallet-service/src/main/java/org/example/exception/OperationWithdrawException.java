package org.example.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class OperationWithdrawException extends WalletException {

    private static final String EXCEPTION_CODE = "002";
    private static final String EXCEPTION_TITLE = "Operation cancelled!";
    private static final String EXCEPTION_DETAIL = "Operation cancelled due low balance on wallet id: %s";

    public OperationWithdrawException(UUID id) {
        super(EXCEPTION_CODE, EXCEPTION_TITLE, String.format(EXCEPTION_DETAIL, id), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
