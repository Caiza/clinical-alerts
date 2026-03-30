package com.caiza.clinical_alerts.telemetry.rules;

import com.caiza.clinical_alerts.telemetry.event.TelemetryReceivedEvent;

import java.math.BigDecimal;

public class OxygenRateRulesStrategy implements TelemetryRulesStrategy {

    @Override
    public boolean supports(String signalType) {
        return "OXYGEN".equalsIgnoreCase(signalType);
    }

    @Override
    public RiskLevel evaluate(TelemetryReceivedEvent event) {
        BigDecimal value = event.measuredValue();

        if (value.intValue() < 85) {
            return RiskLevel.CRITICAL;
        }
        if (value.intValue() > 92){
            return RiskLevel.WARNING;
        }
        return RiskLevel.NORMAL;
    }
}
