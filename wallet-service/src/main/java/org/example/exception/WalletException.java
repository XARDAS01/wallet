package org.example.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public abstract class WalletException extends RuntimeException {

    private final String code;
    private final String title;
    private final String detail;
    private final HttpStatusCode status;

    public WalletException(String code, String title, String detail, HttpStatusCode status, Throwable cause) {
        super(title, cause);
        this.code = code;
        this.title = title;
        this.detail = detail;
        this.status = status;
    }

    public WalletException(String code, String title, String detail, HttpStatusCode status) {
        super(title, null);
        this.code = code;
        this.title = title;
        this.detail = detail;
        this.status = status;
    }
}
