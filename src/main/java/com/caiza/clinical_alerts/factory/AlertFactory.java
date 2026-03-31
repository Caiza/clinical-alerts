package com.caiza.clinical_alerts.factory;

import com.caiza.clinical_alerts.exception.BusinessException;
import com.caiza.clinical_alerts.model.Alert;
import com.caiza.clinical_alerts.telemetry.event.TelemetryReceivedEvent;
import com.caiza.clinical_alerts.telemetry.rules.RiskLevel;
import org.springframework.stereotype.Component;

@Component
public class AlertFactory {

    public Alert createFrom(TelemetryReceivedEvent event, RiskLevel riskLevel) {
        if (event == null || riskLevel == null) {
            throw new BusinessException("event and riskLevel can not be null");
        }
        Alert alert = new Alert();
        alert.setCreatedAt(event.timestamp());
        alert.setSignalType(event.signalType());
        alert.getDevice().setId(event.deviceId());
        alert.setRiskLevel(riskLevel);
        alert.setMeasuredValue(event.measuredValue());
        alert.getPatient().setId(event.patientId());
        return alert;
    }
}
