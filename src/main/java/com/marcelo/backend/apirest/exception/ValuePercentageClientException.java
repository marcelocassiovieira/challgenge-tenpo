package com.marcelo.backend.apirest.exception;

public class ValuePercentageClientException extends RuntimeException{

    String code;

    public ValuePercentageClientException(String code, String message) {
        super(message);
        this.code = code;
    }
}

