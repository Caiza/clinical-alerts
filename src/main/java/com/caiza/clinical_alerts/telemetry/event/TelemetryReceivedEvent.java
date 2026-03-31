package com.caiza.clinical_alerts.telemetry.event;

import com.caiza.clinical_alerts.telemetry.rules.SignalType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record TelemetryReceivedEvent(Long id, Long patientId, Long deviceId, SignalType signalType, BigDecimal measuredValue, Instant timestamp) implements Serializable {
}
