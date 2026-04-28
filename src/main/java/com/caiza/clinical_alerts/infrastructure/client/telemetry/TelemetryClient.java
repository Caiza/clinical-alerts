package com.caiza.clinical_alerts.infrastructure.client.telemetry;

import com.caiza.clinical_alerts.dto.telemetry.TelemetryDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TelemetryClient {

    CompletableFuture<Void> sendTelemetry(TelemetryDTO dto);
    CompletableFuture<Void> sendTelemetryBatch(List<TelemetryDTO> dtos);
}
