package org.example.exception;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WalletGlobalExceptionHandler {

    private static final String INTEGRITY_CONSTRAINT_VIOLATION_CODE = "23514";

    @ExceptionHandler(WalletException.class)
    public ResponseEntity<ProblemDetail> handleWalletException(WalletException ex) {
        return ResponseEntity.of(buildBase(ex)).build();
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<ProblemDetail> handlePSQLException(PSQLException ex) {
        return ResponseEntity.of(buildBase(ex)).build();
    }

    protected ProblemDetail buildBase(WalletException ex) {
        final var base = ProblemDetail.forStatus(ex.getStatus());

        base.setProperty("code", ex.getStatus());
        base.setProperty("title", ex.getTitle());
        base.setProperty("detail", ex.getDetail());

        return base;
    }

    protected ProblemDetail buildBase(PSQLException ex) {
        final var base = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        if (INTEGRITY_CONSTRAINT_VIOLATION_CODE.equals(ex.getSQLState())) {
            base.setProperty("code", HttpStatus.INTERNAL_SERVER_ERROR);
            base.setProperty("title", "Integrity constraint violation");
            base.setProperty("detail", "Constraint violation: Amount must be positive!");
        }

        return base;
    }
}
