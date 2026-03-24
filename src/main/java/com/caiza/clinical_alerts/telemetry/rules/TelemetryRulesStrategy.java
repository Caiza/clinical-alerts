package com.caiza.clinical_alerts.telemetry.rules;

import com.caiza.clinical_alerts.telemetry.event.TelemetryReceivedEvent;

public interface TelemetryRulesStrategy {

    boolean supports(String signalType);
    RiskLevel evaluate(TelemetryReceivedEvent event);
}
