package com.caiza.clinical_alerts.mapper;

import com.caiza.clinical_alerts.dto.telemetry.TelemetryDTO;
import com.caiza.clinical_alerts.dto.telemetry.TelemetryRaw;
import com.caiza.clinical_alerts.model.Telemetry;
import org.springframework.stereotype.Component;

@Component
public class TelemetryMapper {

    public static TelemetryDTO toTelemetryDTO(Telemetry telemetry) {
        return new TelemetryDTO(
                String.valueOf(telemetry.getDeviceId()),
                String.valueOf(telemetry.getPatientId()),
                telemetry.getType(),
                telemetry.getTimestamp(),
                telemetry.getOptionalMetadata(),
                telemetry.getMeasuredValue(),
                telemetry.getUnit()
        );
    }

    public static Telemetry toTelemetry(TelemetryDTO telemetryDTO) {
        return Telemetry.builder()
                .deviceId(Long.valueOf(telemetryDTO.getDeviceId()))
                .patientId(Long.valueOf(telemetryDTO.getPatientId()))
                .type(telemetryDTO.getType())
                .timestamp(telemetryDTO.getTimestamp())
                .optionalMetadata(telemetryDTO.getOptionalMetadata())
                .measuredValue(telemetryDTO.getMeasuredValue())
                .unit(telemetryDTO.getUnit())
                .build();
    }

    public static TelemetryDTO rawToDTO(TelemetryRaw telemetryRaw) {
        if (telemetryRaw == null){
            return null;
        }
        return new TelemetryDTO(
                String.valueOf(telemetryRaw.getDeviceId()),
                String.valueOf(telemetryRaw.getPatientId()),
                telemetryRaw.getType(),
                telemetryRaw.getTimestamp(),
                telemetryRaw.getOptionalMetadata(),
                telemetryRaw.getMeasuredValue(),
                telemetryRaw.getUnit()
        );
    }
}
