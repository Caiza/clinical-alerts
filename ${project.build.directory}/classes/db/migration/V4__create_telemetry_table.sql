CREATE TABLE telemetry (
    id BIGSERIAL PRIMARY KEY,
    device_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    type VARCHAR(100) NOT NULL,
    value VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    optional_metadata TEXT,
    unit VARCHAR(50)
);