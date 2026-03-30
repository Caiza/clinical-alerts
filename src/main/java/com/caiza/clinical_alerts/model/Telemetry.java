package com.caiza.clinical_alerts.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "telemetry")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @CreationTimestamp
    @Column(name = "timestamp", nullable = false, updatable = false)
    private Instant timestamp;
    @Column(name = "optional_metadata")
    private String optionalMetadata;
    @Column(name= "unit")
    private String unit;
    @Column(name = "mensured_value", precision = 10, scale = 2)
    private BigDecimal mensuredValue;


}
