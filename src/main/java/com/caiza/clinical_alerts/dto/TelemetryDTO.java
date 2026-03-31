package com.caiza.clinical_alerts.dto;

import com.caiza.clinical_alerts.telemetry.rules.SignalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryDTO {

    @NotNull
    private Long deviceId;
    @NotNull
    private Long patientId;
    @NotNull
    private SignalType type;
    @NotNull
    private Instant timestamp;
    private String optionalMetadata;
    @NotNull
    private BigDecimal mensuredValue;
    @NotBlank
    private String unit;

}
