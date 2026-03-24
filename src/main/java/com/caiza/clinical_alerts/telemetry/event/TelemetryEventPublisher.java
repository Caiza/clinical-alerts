package com.caiza.clinical_alerts.telemetry.event;

public interface TelemetryEventPublisher {
    void publish(TelemetryReceivedEvent event);
}
