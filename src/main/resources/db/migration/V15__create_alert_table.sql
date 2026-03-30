CREATE TABLE alert (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    device_id BIGINT NOT NULL,
    signal_type VARCHAR(100) NOT NULL,
    measured_value DECIMAL(10,2),
    risk_level VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_alert_patient FOREIGN KEY (patient_id) REFERENCES patient(id),
    CONSTRAINT fk_alert_device FOREIGN KEY (device_id) REFERENCES device(id)
);

-- Índices para performance em consultas
CREATE INDEX idx_alert_patient_id ON alert(patient_id);
CREATE INDEX idx_alert_device_id ON alert(device_id);
CREATE INDEX idx_alert_created_at ON alert(created_at);