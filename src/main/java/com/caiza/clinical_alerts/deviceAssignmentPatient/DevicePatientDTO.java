package com.caiza.clinical_alerts.deviceAssignmentPatient;

import com.caiza.clinical_alerts.device.Device;
import com.caiza.clinical_alerts.patient.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevicePatientDTO {

    @NotNull
    private Long patientId;

    @NotNull
    private Device device;

    @NotNull
    private LocalDate assignmentDate;

    private LocalDate assignmentEndDate;

}
