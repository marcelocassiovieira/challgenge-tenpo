package com.marcelo.backend.apirest.controller;

import com.marcelo.backend.apirest.dto.HistoryDto;
import com.marcelo.backend.apirest.dto.ProcessedValueResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface IHistoryController {


    @ApiOperation(value = "Enpoint que obtiene las llamadas y respuesta paginadas", response = ProcessedValueResponseDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "datos obtenidos satisfactoriamente."),
            @ApiResponse(code = 400, message = "Solicitud incorrecta."),
            @ApiResponse(code = 500, message = "Error interno del servidor.")})
    public ResponseEntity<List<HistoryDto>> getPaginatedHistories(int page, int pageSize);

}
