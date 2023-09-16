package com.marcelo.backend.apirest.service;

import com.marcelo.backend.apirest.dto.ProcessedValueResponseDto;
import com.marcelo.backend.apirest.dto.ValueRequestDto;

public interface IPercentageIncreaseService {

    public ProcessedValueResponseDto getValueWithPercentageIncreased(ValueRequestDto valueRequest);
}
