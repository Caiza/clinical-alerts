package com.caiza.clinical_alerts.telemetry;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "telemetry")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Telemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "device_id", nullable = false)
    private Long deviceId;
    @Column(name = "patient_id", nullable = false)
    private Long patientId;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "value")
    private String value;
    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;
    @Column(name = "optional_metadata")
    private String optionalMetadata;
    @Column(name= "unit")
    private String unit;
}
