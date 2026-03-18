-- Insert device-patient assignments linking devices to patients
INSERT INTO device_patient (patient_id, device_id, assignment_date, assignment_end_date) VALUES
(1, 1, '2026-01-15', NULL),
(2, 2, '2026-01-20', NULL),
(3, 3, '2026-02-01', NULL),
(4, 4, '2026-02-05', NULL),
(5, 1, '2026-02-10', NULL),
(6, 2, '2026-02-15', NULL),
(7, 3, '2026-02-20', NULL),
(8, 4, '2026-02-25', NULL),
(9, 1, '2026-03-01', NULL),
(10, 2, '2026-03-10', NULL);
