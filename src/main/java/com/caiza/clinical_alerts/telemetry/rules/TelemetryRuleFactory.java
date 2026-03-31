package com.caiza.clinical_alerts.telemetry.rules;

import com.caiza.clinical_alerts.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TelemetryRuleFactory {

    private final Map<SignalType, TelemetryRulesStrategy> strategyMap;


    public TelemetryRuleFactory(List<TelemetryRulesStrategy> strategies) {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(
                        TelemetryRulesStrategy::getSupportedSignalType,
                        Function.identity(),
                        (existing, replacement) -> existing
                ));
    }

    public TelemetryRulesStrategy getStrategy(SignalType signalType) {
        return Optional.ofNullable(strategyMap.get(signalType))
                .orElseThrow(() ->
                        new BusinessException("No rule found for signal type: " + signalType));
    }
}
