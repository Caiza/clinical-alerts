package com.caiza.clinical_alerts.telemetry.rules;

import com.caiza.clinical_alerts.telemetry.event.TelemetryReceivedEvent;
import org.springframework.stereotype.Component;

@Component
public class HeartRateRulesStrategy implements TelemetryRulesStrategy {

    @Override
    public RiskLevel evaluate(TelemetryReceivedEvent event) {
        int value = event.measuredValue().intValue();

        if (value < 40 || value > 140) {
            return RiskLevel.CRITICAL;
        }
        if (value < 50 || value > 120){
            return RiskLevel.WARNING;
        }
        return RiskLevel.NORMAL;
    }

    @Override
    public SignalType getSupportedSignalType() {
        return SignalType.HEART_RATE;
    }

}
