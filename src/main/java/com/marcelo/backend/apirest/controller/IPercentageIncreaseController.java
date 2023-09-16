package com.marcelo.backend.apirest.controller;

import com.marcelo.backend.apirest.dto.ProcessedValueResponseDto;
import com.marcelo.backend.apirest.dto.ValueRequestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@ApiOperation(value = "Enpoint que incrementa un porcentage a la suma de dos numeros", response = ProcessedValueResponseDto.class)
@ApiResponses(value = {@ApiResponse(code = 200, message = "Calculo exitoso."),
        @ApiResponse(code = 400, message = "Solicitud incorrecta."),
        @ApiResponse(code = 500, message = "Error interno del servidor.")})
public interface IPercentageIncreaseController {

    ResponseEntity<Object> calculatePercentage(@Valid @RequestBody ValueRequestDto valueRequest, BindingResult result);
}
