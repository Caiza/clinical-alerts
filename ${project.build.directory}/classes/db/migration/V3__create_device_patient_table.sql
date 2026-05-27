CREATE TABLE device_patient (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    device_id BIGINT NOT NULL,
    assignment_date DATE NOT NULL,
    assignment_end_date DATE,
    CONSTRAINT fk_patient
    FOREIGN KEY (patient_id)
    REFERENCES patient(id),
    CONSTRAINT fk_device
    FOREIGN KEY (device_id)
    REFERENCES device(id)
);