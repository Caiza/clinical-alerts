package com.caiza.clinical_alerts.telemetry.event;

import java.io.Serializable;
import java.time.Instant;

public record TelemetryReceivedEvent(Long id, Long patientId, Long deviceId, String type, Double measuredValue, Instant timestamp) implements Serializable {
}
