package com.marcelo.backend.apirest.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marcelo.backend.apirest.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResponseEntity<String> handleArithmeticException(ArithmeticException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en el cálculo: " + ex.getMessage());
    }

    @ExceptionHandler(ValuePercentageClientException.class)
    public ResponseEntity<ErrorDto> handleValuePercentageClientException(ValuePercentageClientException ex) {
        ErrorDto errorDto = ErrorDto.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(RequestLimitException.class)
    public ResponseEntity<ErrorDto> handleRequestLimitException(RequestLimitException ex) {
        ErrorDto errorDto = ErrorDto.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EndpointCallException.class)
    public ResponseEntity<ErrorDto> handleHistoryException(EndpointCallException ex) {
        ErrorDto errorDto = ErrorDto.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
