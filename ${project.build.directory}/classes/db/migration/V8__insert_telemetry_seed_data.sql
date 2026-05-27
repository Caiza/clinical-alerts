-- Insert realistic telemetry data with specified ranges for the last 7 days
-- Heart rate monitor readings (60-100 bpm)
INSERT INTO telemetry (device_id, patient_id, type, value, timestamp, optional_metadata, unit) VALUES
(1, 1, 'heart_rate', '72', '2026-03-10 08:30:00', 'alert_level: normal', 'bpm'),
(1, 1, 'heart_rate', '78', '2026-03-10 14:45:00', 'alert_level: normal', 'bpm'),
(1, 1, 'heart_rate', '68', '2026-03-11 09:15:00', 'alert_level: normal', 'bpm'),
(1, 5, 'heart_rate', '82', '2026-03-12 10:20:00', 'alert_level: normal', 'bpm'),
(1, 5, 'heart_rate', '75', '2026-03-13 11:00:00', 'alert_level: normal', 'bpm'),
(1, 9, 'heart_rate', '88', '2026-03-14 07:30:00', 'alert_level: normal', 'bpm'),
(1, 9, 'heart_rate', '70', '2026-03-15 15:45:00', 'alert_level: normal', 'bpm'),
(1, 9, 'heart_rate', '85', '2026-03-16 12:30:00', 'alert_level: normal', 'bpm'),

-- Blood pressure monitor readings (120/80 range)
(2, 2, 'blood_pressure', '118/76', '2026-03-10 09:00:00', 'alert_level: normal', 'mmHg'),
(2, 2, 'blood_pressure', '125/82', '2026-03-11 10:15:00', 'alert_level: normal', 'mmHg'),
(2, 2, 'blood_pressure', '122/79', '2026-03-12 08:45:00', 'alert_level: normal', 'mmHg'),
(2, 6, 'blood_pressure', '130/85', '2026-03-13 09:30:00', 'alert_level: elevated', 'mmHg'),
(2, 6, 'blood_pressure', '116/74', '2026-03-14 10:00:00', 'alert_level: normal', 'mmHg'),
(2, 10, 'blood_pressure', '128/81', '2026-03-15 08:20:00', 'alert_level: normal', 'mmHg'),
(2, 10, 'blood_pressure', '121/78', '2026-03-16 11:30:00', 'alert_level: normal', 'mmHg'),

-- Glucose sensor readings (70-180 mg/dL)
(3, 3, 'blood_glucose', '105', '2026-03-10 07:00:00', 'alert_level: normal', 'mg/dL'),
(3, 3, 'blood_glucose', '118', '2026-03-10 12:00:00', 'alert_level: normal', 'mg/dL'),
(3, 3, 'blood_glucose', '125', '2026-03-11 07:30:00', 'alert_level: normal', 'mg/dL'),
(3, 7, 'blood_glucose', '140', '2026-03-12 08:00:00', 'alert_level: elevated', 'mg/dL'),
(3, 7, 'blood_glucose', '98', '2026-03-13 07:15:00', 'alert_level: normal', 'mg/dL'),
(3, 7, 'blood_glucose', '112', '2026-03-14 12:30:00', 'alert_level: normal', 'mg/dL'),
(3, 7, 'blood_glucose', '135', '2026-03-15 07:45:00', 'alert_level: elevated', 'mg/dL'),
(3, 7, 'blood_glucose', '115', '2026-03-16 13:00:00', 'alert_level: normal', 'mg/dL'),

-- Oxygen sensor readings (95-100%)
(4, 4, 'oxygen_saturation', '98', '2026-03-10 06:30:00', 'alert_level: normal', '%'),
(4, 4, 'oxygen_saturation', '96', '2026-03-11 07:00:00', 'alert_level: normal', '%'),
(4, 4, 'oxygen_saturation', '99', '2026-03-12 06:45:00', 'alert_level: normal', '%'),
(4, 8, 'oxygen_saturation', '95', '2026-03-13 08:15:00', 'alert_level: normal', '%'),
(4, 8, 'oxygen_saturation', '97', '2026-03-14 07:30:00', 'alert_level: normal', '%'),
(4, 8, 'oxygen_saturation', '100', '2026-03-15 06:00:00', 'alert_level: normal', '%'),
(4, 8, 'oxygen_saturation', '98', '2026-03-16 08:30:00', 'alert_level: normal', '%');
