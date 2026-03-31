package com.caiza.clinical_alerts.mapper;

import com.caiza.clinical_alerts.dto.TelemetryDTO;
import com.caiza.clinical_alerts.model.Telemetry;

public class TelemetryMapper {

    public static TelemetryDTO toTelemetryDTO(Telemetry telemetry) {
        return new TelemetryDTO(
                telemetry.getDeviceId(),
                telemetry.getPatientId(),
                telemetry.getType(),
                telemetry.getTimestamp(),
                telemetry.getOptionalMetadata(),
                telemetry.getMensuredValue(),
                telemetry.getUnit()
        );
    }

    public static Telemetry toTelemetry(TelemetryDTO telemetryDTO) {
        return Telemetry.builder()
                .deviceId(telemetryDTO.getDeviceId())
                .patientId(telemetryDTO.getPatientId())
                .type(telemetryDTO.getType())
                .timestamp(telemetryDTO.getTimestamp())
                .optionalMetadata(telemetryDTO.getOptionalMetadata())
                .mensuredValue(telemetryDTO.getMensuredValue())
                .unit(telemetryDTO.getUnit())
                .build();
    }
}
