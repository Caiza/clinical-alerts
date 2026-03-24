package com.caiza.clinical_alerts.telemetry;

public class TelemetryMapper {

    public static TelemetryDTO toTelemetryDTO(Telemetry telemetry) {
        return new TelemetryDTO(
                telemetry.getDeviceId(),
                telemetry.getPatientId(),
                telemetry.getType(),
                telemetry.getValue(),
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
                .value(telemetryDTO.getValue())
                .timestamp(telemetryDTO.getTimestamp())
                .optionalMetadata(telemetryDTO.getOptionalMetadata())
                .mensuredValue(telemetryDTO.getMensuredValue())
                .unit(telemetryDTO.getUnit())
                .build();
    }
}
