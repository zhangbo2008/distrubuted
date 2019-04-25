package com.hc.distributed.exceptions;

public class ErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ErrorException() {
    }

    public ErrorException(String message) {
        super(message);
    }

    public ErrorException(Throwable cause) {
        super(cause);
    }

    public ErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}