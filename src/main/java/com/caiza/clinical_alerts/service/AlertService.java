package com.caiza.clinical_alerts.service;

import com.caiza.clinical_alerts.factory.AlertFactory;
import com.caiza.clinical_alerts.repository.AlertRepository;
import com.caiza.clinical_alerts.model.Alert;
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
