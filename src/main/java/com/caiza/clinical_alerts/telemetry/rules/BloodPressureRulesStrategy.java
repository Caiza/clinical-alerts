package com.caiza.clinical_alerts.telemetry.rules;

import com.caiza.clinical_alerts.telemetry.event.TelemetryReceivedEvent;
import org.springframework.stereotype.Component;

@Component
public class BloodPressureRulesStrategy implements TelemetryRulesStrategy {

    @Override
    public RiskLevel evaluate(TelemetryReceivedEvent event) {
        int value = event.measuredValue().intValue();

        if (value < 90 || value > 180) {
            return RiskLevel.CRITICAL;
        }
        if (value < 100 || value > 170){
            return RiskLevel.WARNING;
        }
        return RiskLevel.NORMAL;
    }

    @Override
    public SignalType getSupportedSignalType() {
        return SignalType.BLOOD_PRESSURE;
    }

}
