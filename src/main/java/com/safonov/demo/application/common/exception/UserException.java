package com.safonov.demo.application.common.exception;

public class UserException extends AbstractException {
    public UserException(String message) {
        super("UserException! " + message);
    }

    public UserException(String message, String userName) {
        super("UserException! User: " + userName + ". "+ message);
    }

    public UserException(String message, int errorCode) {
        super("UserException! " + message, errorCode);
    }
}
