package com.marcelo.backend.apirest.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorDto {
    private HttpStatus code;
    private String message;

}
