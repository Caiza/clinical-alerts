package com.caiza.clinical_alerts.telemetry;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotNull
    private  Double mensuredValue;
    @NotBlank
    private String unit;

}
