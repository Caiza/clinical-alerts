package com.caiza.clinical_alerts.service;

import com.caiza.clinical_alerts.dto.telemetry.TelemetryDTO;
import com.caiza.clinical_alerts.dto.telemetry.TelemetryRaw;
import com.caiza.clinical_alerts.infrastructure.client.telemetry.TelemetryClient;
import com.caiza.clinical_alerts.mapper.TelemetryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelemetryGatewayService {

    private final TelemetryClient telemetryClient;
    private final TelemetryMapper mapper;

    @Async
    public CompletableFuture<Void> sendTeletry(TelemetryRaw rawData) {
        try {
            TelemetryDTO dto = mapper.rawToDTO(rawData);

            log.info("Sending telemetry for device: {}, type: {}, value: {}{} at {}",
                    rawData.getDeviceId(), rawData.getType(), rawData.getMeasuredValue(), rawData.getUnit(), rawData.getTimestamp());
            return telemetryClient.sendTelemetry(dto);
        } catch (Exception ex) {
            log.error("Exception while sending telemetry for device: {}", rawData.getDeviceId(), ex);
            return CompletableFuture.failedFuture(ex);
        }
    }
}
