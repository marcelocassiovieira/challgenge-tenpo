package com.marcelo.backend.apirest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Objeto de respuesta de los calculos de porcentages
 */
@Getter
@Setter
@ToString
public class ProcessedValueResponseDto {
    private double operandOne;
    private double operandTwo;
    private double percentage;
    private double total;

}
