package com.caiza.clinical_alerts.telemetry.rules;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelemetryRuleFactory {

    private final List<TelemetryRulesStrategy> strategies;

    public TelemetryRuleFactory(List<TelemetryRulesStrategy> strategies) {
        this.strategies = strategies;
    }

    public TelemetryRulesStrategy getStrategy(String signalType) {
        return strategies.stream()
                .filter(strategy -> strategy.supports(signalType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No rule for signal type: " + signalType));
    }
}
