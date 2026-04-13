package com.caiza.clinical_alerts.telemetry.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaTelemetryEventPublisher implements TelemetryEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(KafkaTelemetryEventPublisher.class);

    private final KafkaTemplate<String, TelemetryReceivedEvent> kafkaTemplate;

    public KafkaTelemetryEventPublisher(KafkaTemplate<String, TelemetryReceivedEvent>kafkatemplate){
        this.kafkaTemplate = kafkatemplate;
    }

    @Override
    public void publish(TelemetryReceivedEvent event) {
        kafkaTemplate.send("telemetry-topic", event.patientId().toString(), event).whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Failed to send to Kafka", ex);
            }
        });
    }
}
