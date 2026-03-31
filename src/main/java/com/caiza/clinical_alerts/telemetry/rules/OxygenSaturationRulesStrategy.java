package com.caiza.clinical_alerts.telemetry.rules;

import com.caiza.clinical_alerts.telemetry.event.TelemetryReceivedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OxygenSaturationRulesStrategy implements TelemetryRulesStrategy {

    @Override
    public SignalType getSupportedSignalType() {
        return SignalType.OXYGEN_SATURATION;
    }

    @Override
    public RiskLevel evaluate(TelemetryReceivedEvent event) {
        int value = event.measuredValue().intValue();

        if (value < 85) {
            return RiskLevel.CRITICAL;
        }
        if (value > 92){
            return RiskLevel.WARNING;
        }
        return RiskLevel.NORMAL;
    }
}
