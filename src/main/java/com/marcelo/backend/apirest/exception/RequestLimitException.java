package com.marcelo.backend.apirest.exception;

import org.springframework.http.HttpStatus;

public class RequestLimitException extends RuntimeException{

    private final HttpStatus code;
    public RequestLimitException(HttpStatus code, String message) {
        super(message);
        this.code = code;
    }

    public HttpStatus getCode() {
        return code;
    }
}
