-- Insert 4 sample medical devices into the device table
INSERT INTO device (serial_number, model, manufacture, is_active, device_type) VALUES
(100001, 'CardioMonitor Pro', 'Medtronic', true, 'heart rate monitor'),
(100002, 'BloodPressure Plus', 'Philips', true, 'blood pressure monitor'),
(100003, 'GlucoSense Digital', 'Roche', true, 'glucose sensor'),
(100004, 'OxyPulse Smart', 'Nellcor', true, 'oxygen sensor');
