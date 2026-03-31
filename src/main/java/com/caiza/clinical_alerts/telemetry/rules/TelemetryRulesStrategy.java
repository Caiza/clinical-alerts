package com.caiza.clinical_alerts.telemetry.rules;

import com.caiza.clinical_alerts.telemetry.event.TelemetryReceivedEvent;

public interface TelemetryRulesStrategy {

    SignalType getSupportedSignalType();
    RiskLevel evaluate(TelemetryReceivedEvent event);
}
