package com.caiza.clinical_alerts.alert;

import com.caiza.clinical_alerts.telemetry.event.TelemetryReceivedEvent;
import com.caiza.clinical_alerts.telemetry.rules.RiskLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlertService {

    private final AlertFactory alertFactory;
    private final AlertRepository alertRepository;

    public void generateAlert(TelemetryReceivedEvent event, RiskLevel riskLevel){
        Alert alert = alertFactory.createFrom(event,riskLevel);
        alertRepository.save(alert);
    }
}
