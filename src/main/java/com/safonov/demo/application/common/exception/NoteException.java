package com.safonov.demo.application.common.exception;

public class NoteException extends AbstractException {
    public NoteException(String message) {
        super("NoteException! " + message);
    }

    public NoteException(String message, String userName) {
        super("NoteException! User: " + userName + ". "+ message);
    }

    public NoteException(String message, int errorCode) {
        super("NoteException! " + message, errorCode);
    }
}
