package com.caiza.clinical_alerts.telemetry.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaTelemetryEventPublisher implements TelemetryEventPublisher {

    private final KafkaTemplate<String, TelemetryReceivedEvent> kafkaTemplate;

    public KafkaTelemetryEventPublisher(KafkaTemplate<String, TelemetryReceivedEvent>kafkatemplate){
        this.kafkaTemplate = kafkatemplate;
    }

    @Override
    public void publish(TelemetryReceivedEvent event) {
        kafkaTemplate.send("telemetry-topic", event.patientId().toString(), event);
    }
}
