package com.caiza.clinical_alerts.telemetry.rules;

import com.caiza.clinical_alerts.telemetry.event.TelemetryReceivedEvent;

public class HateRateRulesStrategy implements TelemetryRulesStrategy {

    @Override
    public boolean supports(String signalType) {
        return "HEART_RATE".equalsIgnoreCase(signalType);
    }

    @Override
    public RiskLevel evaluate(TelemetryReceivedEvent event) {
        double value = event.measuredValue();

        if (value < 40 || value > 140) {
            return RiskLevel.CRITICAL;
        }
        if (value < 50 || value > 120){
            return RiskLevel.WARNING;
        }
        return RiskLevel.NORMAL;
    }
}
