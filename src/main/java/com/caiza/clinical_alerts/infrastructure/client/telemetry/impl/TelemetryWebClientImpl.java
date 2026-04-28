package com.caiza.clinical_alerts.infrastructure.client.telemetry.impl;

import com.caiza.clinical_alerts.dto.telemetry.TelemetryDTO;
import com.caiza.clinical_alerts.infrastructure.client.telemetry.TelemetryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelemetryWebClientImpl implements TelemetryClient {

    private final WebClient telemetryWebClient;

    @Override
    public CompletableFuture<Void> sendTelemetry(TelemetryDTO dto) {
        return telemetryWebClient.post()
                .uri("/api/telemetry")
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .timeout(java.time.Duration.ofSeconds(10))
                .doOnSuccess(resp -> log.info("Telemetry sent successfully : {}", dto.getDeviceId()))
                .doOnError(ex -> log.error("Failed to send telemetry for device: {}", dto.getDeviceId(), ex))
                .then()
                .toFuture();
    }

    @Override
    public CompletableFuture<Void> sendTelemetryBatch(List<TelemetryDTO> dtos) {
        if(dtos.isEmpty()){
            return CompletableFuture.completedFuture(null);
        }
        return telemetryWebClient.post()
                .uri("/api/telemetry/batch")
                .bodyValue(dtos)
                .retrieve()
                .toBodilessEntity()
                .timeout(java.time.Duration.ofSeconds(15))
                .doOnSuccess(resp -> log.info("Batch telemetry sent successfully : {} items", dtos.size()))
                .doOnError(ex -> log.error("Failed to send batch telemetry for {} items", dtos.size(), ex))
                .then()
                .toFuture();
    }
}
