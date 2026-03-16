package com.caiza.clinical_alerts.deviceAssignmentPatient;

import com.caiza.clinical_alerts.device.Device;
import com.caiza.clinical_alerts.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "device_patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DevicePatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;
    @Column(name = "assignment_date", nullable = false)
    private LocalDate assignmentDate;
    @Column(name = "assignment_end_date")
    private LocalDate assignmentEndDate;

}
