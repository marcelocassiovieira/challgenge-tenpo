package com.marcelo.backend.apirest.exception;

import org.springframework.http.HttpStatus;

public class EndpointCallException extends RuntimeException{

    private final HttpStatus code;
    public EndpointCallException(HttpStatus code, String message) {
        super(message);
        this.code = code;
    }

    public HttpStatus getCode() {
        return code;
    }
}
