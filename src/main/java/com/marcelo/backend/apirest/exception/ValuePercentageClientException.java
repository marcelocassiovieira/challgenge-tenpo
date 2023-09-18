package com.marcelo.backend.apirest.exception;

import org.springframework.http.HttpStatus;

public class ValuePercentageClientException extends RuntimeException{

    HttpStatus code;

    public ValuePercentageClientException(HttpStatus code, String message) {
        super(message);
        this.code = code;
    }
}

