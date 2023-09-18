package com.marcelo.backend.apirest.client.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcelo.backend.apirest.client.IValuePercentageClient;
import com.marcelo.backend.apirest.entity.ClientPercentage;
import com.marcelo.backend.apirest.exception.ValuePercentageClientException;
import com.marcelo.backend.apirest.client.models.PercentageResponse;
import com.marcelo.backend.apirest.service.IClientePercentageService;
import com.marcelo.backend.apirest.service.impl.ClientePercentageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;


@Component
public class ValuePercentageClientImpl implements IValuePercentageClient {
    private final String URL_BASE = "http://demo4978813.mockable.io/";
    private ObjectMapper objectMapper;
    private WebClient webClient;

    IClientePercentageService clientePercentageService;

    private static final int MAX_RETRIES = 3;

    @Autowired
    public ValuePercentageClientImpl(RestTemplate restTemplate, ObjectMapper objectMapper,
                                     WebClient.Builder webClientBuilder,
                                     ClientePercentageServiceImpl clientePercentageService) {
        this.objectMapper = objectMapper;
        this.webClient = webClientBuilder.baseUrl(URL_BASE).build();
        this.clientePercentageService = clientePercentageService;
    }

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
            throw new ValuePercentageClientException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener el porcentage externo");
        }
    }

    private Mono<String> getPercentageFromClient() {
        return webClient.get()
                .uri("SumWithPercentageIncrease")
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
            ClientPercentage lastlientPercentage = this.clientePercentageService.getLastlientPercentage().get();
            PercentageResponse percentageResponse = new PercentageResponse();
            percentageResponse.setPercentage(lastlientPercentage.getPercentage());
            return percentageResponse;
        } catch (Exception e) {
            throw new ValuePercentageClientException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener el porcentage");
        }
    }

}
