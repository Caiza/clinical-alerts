package com.caiza.clinical_alerts.telemetry;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryDTO {

    @NotNull
    private Long deviceId;
    @NotNull
    private Long patientId;
    @NotBlank
    private String type;
    @NotBlank
    private String value;
    @NotNull
    private Instant timestamp;
    private String optionalMetadata;
    @NotBlank
    private String unit;
}
