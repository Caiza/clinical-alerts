package com.caiza.clinical_alerts.dto.telemetry;

import com.caiza.clinical_alerts.telemetry.rules.SignalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelemetryRaw {

    private String deviceId;
    private String patientId;
    private SignalType type;
    private Instant timestamp;
    private String optionalMetadata;
    private BigDecimal measuredValue;
    private String unit;
    private String eventId;
}
