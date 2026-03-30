package com.caiza.clinical_alerts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private Instant assignmentDate;
    @Column(name = "assignment_end_date")
    private Instant assignmentEndDate;

}
