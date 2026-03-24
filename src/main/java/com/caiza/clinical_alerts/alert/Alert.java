package com.caiza.clinical_alerts.alert;

import com.caiza.clinical_alerts.telemetry.rules.RiskLevel;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Alert {

    private Long patientId;
    private Long devicedId;
    private String signalType;
    private Double measuredValue;
    private RiskLevel riskLevel;
    private LocalDateTime createAt;


}
