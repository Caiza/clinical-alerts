package com.caiza.clinical_alerts.telemetry.rules;

import com.caiza.clinical_alerts.telemetry.event.TelemetryReceivedEvent;

public class OxygenRateRulesStrategy implements TelemetryRulesStrategy {

    @Override
    public boolean supports(String signalType) {
        return "OXYGEN".equalsIgnoreCase(signalType);
    }

    @Override
    public RiskLevel evaluate(TelemetryReceivedEvent event) {
        double value = event.measuredValue();

        if (value < 85) {
            return RiskLevel.CRITICAL;
        }
        if (value > 92){
            return RiskLevel.WARNING;
        }
        return RiskLevel.NORMAL;
    }
}
