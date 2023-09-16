package com.marcelo.backend.apirest.exception;

public class HistoryException extends RuntimeException{

    public HistoryException(String message) {
        super(message);
    }

    public HistoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
