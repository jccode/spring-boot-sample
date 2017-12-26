package com.github.jccode.springbootsample.core.exception;

public class RestException extends RuntimeException {

    public RestException(String message) {
        super(message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestException(Throwable cause) {
        super(cause);
    }
}
