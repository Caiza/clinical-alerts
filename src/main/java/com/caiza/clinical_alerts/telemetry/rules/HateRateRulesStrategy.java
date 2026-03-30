package com.caiza.clinical_alerts.telemetry.rules;

import com.caiza.clinical_alerts.telemetry.event.TelemetryReceivedEvent;

import java.math.BigDecimal;

public class HateRateRulesStrategy implements TelemetryRulesStrategy {

    @Override
    public boolean supports(String signalType) {
        return "HEART_RATE".equalsIgnoreCase(signalType);
    }

    @Override
    public RiskLevel evaluate(TelemetryReceivedEvent event) {
        BigDecimal value = event.measuredValue();

        if (value.intValue() < 40 || value.intValue() > 140) {
            return RiskLevel.CRITICAL;
        }
        if (value.intValue() < 50 || value.intValue() > 120){
            return RiskLevel.WARNING;
        }
        return RiskLevel.NORMAL;
    }
}
