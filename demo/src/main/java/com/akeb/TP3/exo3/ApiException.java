package com.akeb.TP3.exo3;

public class ApiException extends Exception {
    private final ErrorType errorType;

    public enum ErrorType {
        CONNECTION_ERROR,
        INVALID_FORMAT,
        NOT_FOUND,
        UNAUTHORIZED
    }

    public ApiException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
