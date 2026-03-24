package com.caiza.clinical_alerts.alert;

import com.caiza.clinical_alerts.telemetry.event.TelemetryReceivedEvent;
import com.caiza.clinical_alerts.telemetry.rules.RiskLevel;
import org.springframework.stereotype.Component;

@Component
public class AlertFactory {

    public Alert createFrom(TelemetryReceivedEvent event, RiskLevel riskLevel){
        return new Alert.AlertBuilder()
                .patientId()
                .devicedId()
                .signalType()
                .measuredValue()
                .riskLevel()
                .createAt()
                .build;
    }
}
