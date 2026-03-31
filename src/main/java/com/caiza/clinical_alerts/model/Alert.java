package com.caiza.clinical_alerts.model;

import com.caiza.clinical_alerts.telemetry.rules.RiskLevel;
import com.caiza.clinical_alerts.telemetry.rules.SignalType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alert")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;
    @Enumerated(EnumType.STRING)
    private SignalType signalType;
    private BigDecimal measuredValue;
    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;
    private Instant createdAt;
}
