package com.marcelo.backend.apirest.controller.impl;

import com.marcelo.backend.apirest.controller.IPercentageIncreaseController;
import com.marcelo.backend.apirest.dto.ProcessedValueResponseDto;
import com.marcelo.backend.apirest.dto.ValueRequestDto;
import com.marcelo.backend.apirest.service.IPercentageIncreaseService;
import com.marcelo.backend.config.SwaggerConfig;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(tags = { SwaggerConfig.PORCENTAGE_TAG })
public class PercentageIncreaseController implements IPercentageIncreaseController {
    private IPercentageIncreaseService percentageIncreaseService;
    @Autowired
    public PercentageIncreaseController(IPercentageIncreaseService percentageIncreaseService) {
        this.percentageIncreaseService = percentageIncreaseService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> calculatePercentage(@Valid @RequestBody ValueRequestDto valueRequest, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            return validateRequest(result);
        }
        ProcessedValueResponseDto processedValueResponseDto;
        processedValueResponseDto = percentageIncreaseService.getValueWithPercentageIncreased(valueRequest);
        return new ResponseEntity<Object>(processedValueResponseDto, HttpStatus.OK);
    }

    private static ResponseEntity<Object> validateRequest(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
