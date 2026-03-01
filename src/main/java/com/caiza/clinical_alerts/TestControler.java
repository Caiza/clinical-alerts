package com.caiza.clinical_alerts;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestControler {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/publish")
    public String publish(@RequestParam String message) {
        kafkaTemplate.send("vital.readings", message);
        return "Message sent: " + message;
    }
}