package com.caiza.clinical_alerts.telemetry.event;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record TelemetryReceivedEvent(Long id, Long patientId, Long deviceId, String type, BigDecimal measuredValue, Instant timestamp) implements Serializable {
}
