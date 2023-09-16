package com.marcelo.backend.apirest.repositories;

import com.marcelo.backend.apirest.entity.ClientPercentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientPercentageRepositoryTest {

    @Mock
    private ClientPercentageRepository clientPercentageRepository;

    private ClientPercentage clientPercentage;

    @BeforeEach
    public void setUp() {
        clientPercentage = new ClientPercentage();
        clientPercentage.setId(1L);
        clientPercentage.setPercentage(10.0);
        when(clientPercentageRepository.getNewerPercentage()).thenReturn(Optional.of(clientPercentage));
    }

    @Test
    public void testGetNewerPercentageWhenInvokedThenCalledOnce() {
        // Arrange is done in setUp()

        // Act
        clientPercentageRepository.getNewerPercentage();

        // Assert
        verify(clientPercentageRepository, times(1)).getNewerPercentage();
    }

    @Test
    public void testGetNewerPercentageWhenInvokedThenReturnsOptionalOfClientPercentage() {
        // Arrange is done in setUp()

        // Act
        Optional<ClientPercentage> result = clientPercentageRepository.getNewerPercentage();

        // Assert
        assertTrue(result.isPresent());
        assertEquals(clientPercentage, result.get());
    }
}