ALTER TABLE telemetry
    ADD COLUMN mensured_value DECIMAL(10,2);

UPDATE telemetry
SET mensured_value = mensured_value::DECIMAL(10,2);

