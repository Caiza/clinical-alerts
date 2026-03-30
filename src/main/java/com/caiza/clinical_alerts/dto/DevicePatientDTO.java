package com.caiza.clinical_alerts.dto;

import com.caiza.clinical_alerts.model.Device;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevicePatientDTO {

    @NotNull
    private Long patientId;

    @NotNull
    private Device device;

    @NotNull
    private Instant assignmentDate;

    private Instant assignmentEndDate;

}
