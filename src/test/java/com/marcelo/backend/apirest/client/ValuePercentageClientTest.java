package com.marcelo.backend.apirest.client;

import com.marcelo.backend.apirest.client.models.PercentageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValuePercentageClientTest {

    @Mock
    private IValuePercentageClient valuePercentageClient;

    @BeforeEach
    public void setup() {
        reset(valuePercentageClient);
    }

    @Test
    public void testGetPercentageWhenCalledThenIsExecuted() {
        // Arrange
        PercentageResponse response = new PercentageResponse();
        response.setPercentage(50.0);
        when(valuePercentageClient.getPercentage()).thenReturn(response);

        // Act
        valuePercentageClient.getPercentage();

        // Assert
        verify(valuePercentageClient, times(1)).getPercentage();
    }

    @Test
    public void testGetPercentageWhenCalledThenReturnsCorrectValue() {
        // Arrange
        PercentageResponse response = new PercentageResponse();
        response.setPercentage(50.0);
        when(valuePercentageClient.getPercentage()).thenReturn(response);

        // Act
        PercentageResponse result = valuePercentageClient.getPercentage();

        // Assert
        assertEquals(response.getPercentage(), result.getPercentage());
    }
}