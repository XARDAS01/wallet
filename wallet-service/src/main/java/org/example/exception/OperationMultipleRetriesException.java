package org.example.exception;

import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.UUID;

public class OperationMultipleRetriesException extends WalletException {

    private static final String EXCEPTION_CODE = "004";
    private static final String EXCEPTION_TITLE = "Operation failed after multiple retries!";
    private static final String EXCEPTION_DETAIL = "Operation with amount: %s for walletId: %s failed after multiple retries.";

    public OperationMultipleRetriesException(BigDecimal amount, UUID id) {
        super(EXCEPTION_CODE, EXCEPTION_TITLE, String.format(EXCEPTION_DETAIL, amount, id), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
