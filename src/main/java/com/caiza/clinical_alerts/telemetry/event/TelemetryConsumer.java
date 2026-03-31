package com.caiza.clinical_alerts.telemetry.event;

import com.caiza.clinical_alerts.service.AlertService;
import com.caiza.clinical_alerts.telemetry.rules.RiskLevel;
import com.caiza.clinical_alerts.telemetry.rules.TelemetryRuleFactory;
import com.caiza.clinical_alerts.telemetry.rules.TelemetryRulesStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TelemetryConsumer {

    private final TelemetryRuleFactory ruleFactory;
    private final AlertService alertService;

    @KafkaListener(topics = "telemetry-topic", groupId = "telemtry-processor")
    public void consume(TelemetryReceivedEvent event){
        TelemetryRulesStrategy strategy = ruleFactory.getStrategy(event.signalType());
        RiskLevel riskLevel = strategy.evaluate(event);

        if(riskLevel != RiskLevel.NORMAL) {
            alertService.generateAlert(event, riskLevel);
        }
    }

}
