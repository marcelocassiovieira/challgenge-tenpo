package com.marcelo.backend.apirest.service;

import com.marcelo.backend.apirest.dto.ProcessedValueResponseDto;
import com.marcelo.backend.apirest.dto.ValueRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PercentageIncreaseServiceTest {

    @Mock
    private IPercentageIncreaseService iPercentageIncreaseService;

    @Test
    public void testGetValueWithPercentageIncreasedWhenValueAndPercentageProvidedThenReturnIncreasedValue() {
        ValueRequestDto valueRequestDto = new ValueRequestDto();
        valueRequestDto.setOperandOne(100D);
        valueRequestDto.setOperandTwo(10D);
        ProcessedValueResponseDto expectedResponse = new ProcessedValueResponseDto();
        expectedResponse.setOperandOne(100);
        expectedResponse.setOperandTwo(10);
        expectedResponse.setPercentage(10);
        expectedResponse.setTotal(110);
        when(iPercentageIncreaseService.getValueWithPercentageIncreased(valueRequestDto)).thenReturn(expectedResponse);

        ProcessedValueResponseDto actualResponse = iPercentageIncreaseService.getValueWithPercentageIncreased(valueRequestDto);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    public void testGetValueWithPercentageIncreasedWhenValueIsZeroThenReturnZero() {
        ValueRequestDto valueRequestDto = new ValueRequestDto();
        valueRequestDto.setOperandOne(0D);
        valueRequestDto.setOperandTwo(10D);
        ProcessedValueResponseDto expectedResponse = new ProcessedValueResponseDto();
        expectedResponse.setOperandOne(0);
        expectedResponse.setOperandTwo(10);
        expectedResponse.setPercentage(10);
        expectedResponse.setTotal(0);
        when(iPercentageIncreaseService.getValueWithPercentageIncreased(valueRequestDto)).thenReturn(expectedResponse);

        ProcessedValueResponseDto actualResponse = iPercentageIncreaseService.getValueWithPercentageIncreased(valueRequestDto);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    public void testGetValueWithPercentageIncreasedWhenPercentageIsZeroThenReturnValue() {
        ValueRequestDto valueRequestDto = new ValueRequestDto();
        valueRequestDto.setOperandOne(100D);
        valueRequestDto.setOperandTwo(0D);
        ProcessedValueResponseDto expectedResponse = new ProcessedValueResponseDto();
        expectedResponse.setOperandOne(100);
        expectedResponse.setOperandTwo(0);
        expectedResponse.setPercentage(0);
        expectedResponse.setTotal(100);
        when(iPercentageIncreaseService.getValueWithPercentageIncreased(valueRequestDto)).thenReturn(expectedResponse);

        ProcessedValueResponseDto actualResponse = iPercentageIncreaseService.getValueWithPercentageIncreased(valueRequestDto);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}