package org.example.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class WalletNotFoundException extends WalletException {

    private static final String EXCEPTION_CODE = "003";
    private static final String EXCEPTION_TITLE = "Wallet not found!";
    private static final String EXCEPTION_DETAIL = "Wallet with id: %s not found!";

    public WalletNotFoundException(UUID id) {
        super(EXCEPTION_CODE, EXCEPTION_TITLE, String.format(EXCEPTION_DETAIL, id), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
