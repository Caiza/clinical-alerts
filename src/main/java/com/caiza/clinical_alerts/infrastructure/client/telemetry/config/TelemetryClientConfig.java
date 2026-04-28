package com.caiza.clinical_alerts.infrastructure.client.telemetry.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.retry.RetryOperator;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TelemetryClientConfig {

    @Value("${telemetry.api.base-url:http://localhost:8080/api}")
    private String baseUrl;

    @Value("${telemetry.api.key:}")
    private String apiKey;

    @Bean
    public WebClient telemetryWebClient(WebClient.Builder builder,
                                        CircuitBreakerRegistry circuitBreakerRegistry,
                                        RetryRegistry retryRegistry) {

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("telemetryApi");
        Retry retry = retryRegistry.retry("telemetryApi");


        return builder
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-API-KEY", apiKey)

                // Forma correta de aplicar os operadores do Resilience4j
                .filter((request, next) -> next.exchange(request)
                        .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                        .transformDeferred(RetryOperator.of(retry))
                )
                .build();
    }
}