package com.marcelo.backend.apirest.service.impl;

import com.marcelo.backend.apirest.client.IValuePercentageClient;
import com.marcelo.backend.apirest.dto.ProcessedValueResponseDto;
import com.marcelo.backend.apirest.dto.ValueRequestDto;
import com.marcelo.backend.apirest.entity.ClientPercentage;
import com.marcelo.backend.apirest.service.IClientePercentageService;
import com.marcelo.backend.apirest.service.IPercentageIncreaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PercentageIncreaseServiceImpl implements IPercentageIncreaseService {
    private IValuePercentageClient percentageClient;
    private IClientePercentageService clientePercentageService;
    @Autowired
    public PercentageIncreaseServiceImpl(IValuePercentageClient percentageClient,
                                         ClientePercentageServiceImpl clientePercentageService) {
        this.percentageClient = percentageClient;
        this.clientePercentageService = clientePercentageService;
    }

    @Override
    public ProcessedValueResponseDto getValueWithPercentageIncreased(ValueRequestDto valueRequest) {
        return getPercentageAndCalculate(valueRequest);
    }

    /**
     * Obtiene el porcentage desde el cliente o base de datos y calcula
     * @param valueRequest
     * @return ProcessedValueResponseDto
     */
    private ProcessedValueResponseDto getPercentageAndCalculate(ValueRequestDto valueRequest) {
        ProcessedValueResponseDto dto = new ProcessedValueResponseDto();
        Double percentage = getPercentageFromClient();
        Double value = getPercentageAndCalculate(valueRequest.getOperandOne(), valueRequest.getOperandTwo(), percentage);

        //Acá podria extraer a una clase implementando aspectos y usar asincronia para evitar sumar tiempo...
        if(Objects.nonNull(percentage))
            clientePercentageService.save(mapClientePercentage(percentage));

        dto.setPercentage(percentage);
        dto.setOperandOne(valueRequest.getOperandOne());
        dto.setOperandTwo(valueRequest.getOperandTwo());
        dto.setTotal(value);
        return dto;
    }

    /**
     * Suma los dos primeros numero y aplica el porcentage al resultado
     * @param operandOne
     * @param operandTwo
     * @param percentage
     * @return double
     */
    private Double getPercentageAndCalculate(double operandOne, double operandTwo, double percentage) {
        double suma = operandOne + operandTwo;
        double incremento = (percentage / 100) * suma;
        return suma + incremento;
    }

    private Double getPercentageFromClient() {
        return percentageClient.getPercentage().getPercentage();
    }

    public ClientPercentage mapClientePercentage(Double value){
        ClientPercentage clientPercentage = new ClientPercentage();
        clientPercentage.setPercentage(value);

        return clientPercentage;
    }

}
