package com.marcelo.backend.apirest.client.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcelo.backend.apirest.client.IValuePercentageClient;
import com.marcelo.backend.apirest.entity.ClientPercentage;
import com.marcelo.backend.apirest.exception.ValuePercentageClientException;
import com.marcelo.backend.apirest.client.models.PercentageResponse;
import com.marcelo.backend.apirest.service.IClientePercentageService;
import com.marcelo.backend.apirest.service.impl.ClientePercentageServiceImpl;
import com.marcelo.backend.apirest.utils.ApiConstantes;
import com.marcelo.backend.apirest.utils.ExceptionsConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;


@Component
public class ValuePercentageClientImpl implements IValuePercentageClient {
    private ObjectMapper objectMapper;
    private WebClient webClient;
    private IClientePercentageService clientePercentageService;
    private static final int MAX_RETRIES = 3;

    @Autowired
    public ValuePercentageClientImpl(ObjectMapper objectMapper,
                                     WebClient.Builder webClientBuilder,
                                     ClientePercentageServiceImpl clientePercentageService) {
        this.objectMapper = objectMapper;
        this.webClient = webClientBuilder.baseUrl(ApiConstantes.CLIENT_MOCK).build();
        this.clientePercentageService = clientePercentageService;
    }

    /**
     * Obtiene el porcentage del cliente externo, si falla, busca en la base el ultimo valor guardado
     * @return
     */
    @Cacheable(value = "percentage_cache", key = "'percentage'")
    @Override
    @Retryable(value = { ValuePercentageClientException.class }, maxAttempts = MAX_RETRIES)
    public PercentageResponse getPercentage() {
        try {
            Mono<String> percentageMono = getPercentageFromClient();
            String percentageFromClient = percentageMono.block();
            PercentageResponse percentageResponse = mapper(percentageFromClient);
            return percentageResponse;
        } catch (HttpClientErrorException | HttpServerErrorException | WebClientResponseException e) {
            throw new ValuePercentageClientException(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionsConstantes.ERROR_OBTENER_PORCENTAGE);
        }
    }

    private Mono<String> getPercentageFromClient() {
        return webClient.get()
                .uri(ApiConstantes.CLIENT_URI)
                .retrieve()
                .bodyToMono(String.class);
    }

    private PercentageResponse mapper(String json) {
        try {
            return objectMapper.readValue(json, PercentageResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Recover
    private PercentageResponse getPercentageResponseFromBase() {
        try {
            ClientPercentage lastClientPercentage = this.clientePercentageService.getLastlientPercentage().get();
            PercentageResponse percentageResponse = new PercentageResponse();
            percentageResponse.setPercentage(lastClientPercentage.getPercentage());
            return percentageResponse;
        } catch (Exception e) {
            throw new ValuePercentageClientException(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionsConstantes.ERROR_OBTENER_PORCENTAGE_BASE);
        }
    }

}
