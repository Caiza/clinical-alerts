package com.caiza.clinical_alerts.mapper;

import com.caiza.clinical_alerts.dto.telemetry.TelemetryDTO;
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
                telemetry.getUnit(),
                String.valueOf(telemetry.getEventId())
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
                .eventId(telemetryDTO.getEventId())
                .build();
    }

}
