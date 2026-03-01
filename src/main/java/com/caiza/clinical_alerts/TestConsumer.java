package com.caiza.clinical_alerts;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

    @KafkaListener(topics = "vital.readings", groupId = "clinical-group")
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
}