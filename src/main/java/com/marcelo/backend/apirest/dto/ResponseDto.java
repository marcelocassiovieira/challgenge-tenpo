package com.marcelo.backend.apirest.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {
    private Long id;
    private double operandOne;
    private double operandTwo;
    private double percentage;
    private double total;
}
