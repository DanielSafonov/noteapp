package com.safonov.demo.application.common.exception;

public class PermissionException extends AbstractException {
    public PermissionException(String message) {
        super("PermissionException! " + message);
    }

    public PermissionException(String message, String userName) {
        super("PermissionException! User: " + userName + ". "+ message);
    }

    public PermissionException(String message, int errorCode) {
        super("PermissionException! " + message, errorCode);
    }
}
