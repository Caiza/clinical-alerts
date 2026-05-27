CREATE TABLE device (
    id BIGSERIAL PRIMARY KEY,
    serial_number INTEGER NOT NULL UNIQUE,
    model VARCHAR(100) NOT NULL,
    manufacture VARCHAR(50) NOT NULL,
    is_active BOOLEAN NOT NULL,
    device_type VARCHAR(100) NOT NULL
);