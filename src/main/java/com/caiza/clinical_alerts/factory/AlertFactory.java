package com.caiza.clinical_alerts.factory;

import com.caiza.clinical_alerts.exception.BusinessException;
import com.caiza.clinical_alerts.model.Alert;
import com.caiza.clinical_alerts.model.Device;
import com.caiza.clinical_alerts.model.Patient;
import com.caiza.clinical_alerts.telemetry.event.TelemetryReceivedEvent;
import com.caiza.clinical_alerts.telemetry.rules.RiskLevel;
import org.springframework.stereotype.Component;

@Component
public class AlertFactory {

    public Alert createFrom(TelemetryReceivedEvent event, RiskLevel riskLevel) {
        if (event == null || riskLevel == null) {
            throw new BusinessException("event or riskLevel can not be null");
        }
        Device device = new Device();
        device.setId(event.deviceId());
        Patient patient = new Patient();
        patient.setId(event.patientId());
        Alert alert = new Alert();
        alert.setCreatedAt(event.timestamp());
        alert.setSignalType(event.signalType());
        alert.setDevice(device);
        alert.setRiskLevel(riskLevel);
        alert.setMeasuredValue(event.measuredValue());
        alert.setPatient(patient);
        return alert;
    }
}
