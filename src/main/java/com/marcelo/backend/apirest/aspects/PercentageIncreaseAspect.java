package com.marcelo.backend.apirest.aspects;

import com.marcelo.backend.apirest.dto.ProcessedValueResponseDto;
import com.marcelo.backend.apirest.entity.History;
import com.marcelo.backend.apirest.entity.ResponseApi;
import com.marcelo.backend.apirest.exception.RequestLimitException;
import com.marcelo.backend.apirest.service.IHistoryService;
import com.marcelo.backend.apirest.utils.ExceptionsConstantes;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;

@Aspect
@Component
public class PercentageIncreaseAspect {

    @Autowired
    private IHistoryService historyService;

    @Pointcut("execution(* com.marcelo.backend.apirest.controller.impl.*.*(..))")
    private void anyControllerMethod() {
    }

    /**
     * Guarda las llamadas y las respuestas asincronicamente
     * @param joinPoint
     * @param request
     * @param response
     */
    @AfterReturning(pointcut = "anyControllerMethod() && args(request, ..)", returning = "response")
    public void logEndpointCall(JoinPoint joinPoint, Object request, ResponseEntity<Object> response) {

        CompletableFuture.runAsync(() -> {
            try {
                String endpoint = joinPoint.getSignature().getName();
                if (response != null && response.getBody() instanceof ProcessedValueResponseDto) {
                    ProcessedValueResponseDto processedValueResponseDto = (ProcessedValueResponseDto) response.getBody();
                    if (response.getStatusCode().is2xxSuccessful()) {
                        History history = getHistory(endpoint, processedValueResponseDto);
                        historyService.save(history);
                    }
                }
            } catch (Exception e) {
                throw new RequestLimitException(HttpStatus.TOO_MANY_REQUESTS, ExceptionsConstantes.ERROR_HISTORY);
            }
        });
    }

    private static History getHistory(String endpoint, ProcessedValueResponseDto processedValueResponseDto) {
        History history = new History();
        history.setCall(endpoint);
        ResponseApi responseApi = new ResponseApi();
        responseApi.setPercentage(processedValueResponseDto.getPercentage());
        responseApi.setOperandOne(processedValueResponseDto.getOperandOne());
        responseApi.setOperandTwo(processedValueResponseDto.getOperandTwo());
        responseApi.setTotal(processedValueResponseDto.getTotal());
        history.setResponse(responseApi);
        return history;
    }
}
