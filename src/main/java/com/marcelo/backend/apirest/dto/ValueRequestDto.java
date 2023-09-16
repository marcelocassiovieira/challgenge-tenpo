package com.marcelo.backend.apirest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Objeto que recibe el controlador para los calculos de porcentage
 */

@Getter
@Setter
public class ValueRequestDto {

    @NotNull
    private Double operandOne;
    @NotNull
    private Double operandTwo;

}
