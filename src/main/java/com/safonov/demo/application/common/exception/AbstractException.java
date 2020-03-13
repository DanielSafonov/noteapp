package com.safonov.demo.application.common.exception;


import lombok.Getter;

@Getter
public abstract class AbstractException extends RuntimeException {
    private int errorCode;
    private String userName;

    public AbstractException() { }

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
