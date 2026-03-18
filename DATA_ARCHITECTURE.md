# Clinical Alerts API - Data Architecture & Relationships

## Database Schema Overview

```
┌─────────────────────────────────────────────────────────────────────┐
│                    CLINICAL ALERTS SYSTEM                           │
└─────────────────────────────────────────────────────────────────────┘

┌──────────────────────┐
│     PATIENT          │
├──────────────────────┤
│ id (PK)              │  ◄─────────────┐
│ name                 │                 │
│ date_of_birth        │                 │ 1:M Relationship
│ gender               │                 │ (One patient has many devices)
│ status (active)      │                 │
│ number_id (UNIQUE)   │                 │
└──────────────────────┘                 │
                                         │
                            ┌────────────────────────┐
                            │   DEVICE_PATIENT (JT)  │
                            ├────────────────────────┤
                            │ id (PK)                │
                            │ patient_id (FK) ───────┼──► PATIENT
                            │ device_id (FK) ────┐   │
                            │ assignment_date    │   │
                            │ assignment_end_date│   │
                            └────────────────────┴───┘
                                         ▲
                                         │
                            ┌────────────┘
                            │
                            │ M:1 Relationship
                            │ (One device assigned to many patients)
                            │
                            ▼
┌──────────────────────┐
│     DEVICE           │
├──────────────────────┤
│ id (PK)              │  ◄─────────────┐
│ serial_number (UNIQUE)               │
│ model                │                │ 1:M Relationship
│ manufacture          │                │ (One device has many readings)
│ is_active            │                │
│ device_type          │                │
└──────────────────────┘                │
                                         │
                            ┌────────────────────────┐
                            │     TELEMETRY          │
                            ├────────────────────────┤
                            │ id (PK)                │
                            │ device_id (FK) ────────┼──► DEVICE
                            │ patient_id (FK) ───────┼──► PATIENT
                            │ type                   │
                            │ value                  │
                            │ timestamp              │
                            │ optional_metadata      │
                            │ unit                   │
                            └────────────────────────┘
```

---

## Data Flow Example: How a Patient Gets Monitored

```
Step 1: Patient Registration
┌─────────────────┐
│  John Smith     │  ─► Inserted into PATIENT table
│  DOB: 1955      │     ID: 1
│  Male, Active   │
└─────────────────┘

Step 2: Device Assignment
┌──────────────────────┐
│  Heart Rate Monitor  │  ─► Already in DEVICE table
│  Serial: 100001      │     ID: 1
│  Active              │
└──────────────────────┘

Step 3: Link Patient to Device
┌──────────────────────────────────────────────┐
│  DEVICE_PATIENT Entry                        │
│  patient_id: 1 (John Smith)                  │
│  device_id: 1 (Heart Rate Monitor)           │
│  assignment_date: 2026-01-15                 │
│  assignment_end_date: NULL (ongoing)         │
└──────────────────────────────────────────────┘

Step 4: Device Sends Telemetry Data
┌──────────────────────────────────────────────┐
│  TELEMETRY Records (multiple readings)       │
│                                              │
│  Reading 1:                                  │
│  • device_id: 1 (Heart Rate Monitor)         │
│  • patient_id: 1 (John Smith)                │
│  • type: "heart_rate"                        │
│  • value: "72"                               │
│  • timestamp: 2026-03-10 08:30:00            │
│  • unit: "bpm"                               │
│  • alert_level: "normal"                     │
│                                              │
│  Reading 2:                                  │
│  • device_id: 1                              │
│  • patient_id: 1                             │
│  • type: "heart_rate"                        │
│  • value: "78"                               │
│  • timestamp: 2026-03-10 14:45:00            │
│  • unit: "bpm"                               │
│  • alert_level: "normal"                     │
│  [... more readings ...]                     │
└──────────────────────────────────────────────┘
```

---

## Seed Data Distribution

### Patient Monitoring Setup

```
Device: Heart Rate Monitor (ID: 1)
├── Patient 1: John Smith (assigned 2026-01-15)
│   ├── Reading: 72 bpm (2026-03-10 08:30)
│   ├── Reading: 78 bpm (2026-03-10 14:45)
│   └── Reading: 68 bpm (2026-03-11 09:15)
├── Patient 5: Michael Davis (assigned 2026-02-10)
│   ├── Reading: 82 bpm (2026-03-12 10:20)
│   └── Reading: 75 bpm (2026-03-13 11:00)
└── Patient 9: David Taylor (assigned 2026-03-01)
    ├── Reading: 88 bpm (2026-03-14 07:30)
    ├── Reading: 70 bpm (2026-03-15 15:45)
    └── Reading: 85 bpm (2026-03-16 12:30)

Device: Blood Pressure Monitor (ID: 2)
├── Patient 2: Mary Johnson (assigned 2026-01-20)
│   ├── Reading: 118/76 mmHg (2026-03-10 09:00)
│   ├── Reading: 125/82 mmHg (2026-03-11 10:15)
│   └── Reading: 122/79 mmHg (2026-03-12 08:45)
├── Patient 6: Linda Miller (assigned 2026-02-15)
│   ├── Reading: 130/85 mmHg (2026-03-13 09:30) [ELEVATED]
│   └── Reading: 116/74 mmHg (2026-03-14 10:00)
└── Patient 10: Jennifer Anderson (assigned 2026-03-10)
    ├── Reading: 128/81 mmHg (2026-03-15 08:20)
    └── Reading: 121/78 mmHg (2026-03-16 11:30)

Device: Glucose Sensor (ID: 3)
├── Patient 3: Robert Williams (assigned 2026-02-01)
│   ├── Reading: 105 mg/dL (2026-03-10 07:00)
│   ├── Reading: 118 mg/dL (2026-03-10 12:00)
│   └── Reading: 125 mg/dL (2026-03-11 07:30)
└── Patient 7: James Wilson (assigned 2026-02-20)
    ├── Reading: 140 mg/dL (2026-03-12 08:00) [ELEVATED]
    ├── Reading: 98 mg/dL (2026-03-13 07:15)
    ├── Reading: 112 mg/dL (2026-03-14 12:30)
    ├── Reading: 135 mg/dL (2026-03-15 07:45) [ELEVATED]
    └── Reading: 115 mg/dL (2026-03-16 13:00)

Device: Oxygen Sensor (ID: 4)
├── Patient 4: Patricia Brown (assigned 2026-02-05)
│   ├── Reading: 98% (2026-03-10 06:30)
│   ├── Reading: 96% (2026-03-11 07:00)
│   └── Reading: 99% (2026-03-12 06:45)
└── Patient 8: Barbara Moore (assigned 2026-02-25)
    ├── Reading: 95% (2026-03-13 08:15)
    ├── Reading: 97% (2026-03-14 07:30)
    ├── Reading: 100% (2026-03-15 06:00)
    └── Reading: 98% (2026-03-16 08:30)
```

---

## API Query Examples

### 1. Get Patient with All Assigned Devices
```sql
SELECT p.*, d.*, dp.assignment_date
FROM patient p
JOIN device_patient dp ON p.id = dp.patient_id
JOIN device d ON dp.device_id = d.id
WHERE p.id = 1;
```

**Result**: John Smith + Heart Rate Monitor (assigned 2026-01-15)

---

### 2. Get Latest Telemetry for a Device
```sql
SELECT * FROM telemetry
WHERE device_id = 1
ORDER BY timestamp DESC
LIMIT 5;
```

**Result**: 5 most recent heart rate readings from Heart Rate Monitor

---

### 3. Get Elevated Alerts
```sql
SELECT t.*, p.name, d.model
FROM telemetry t
JOIN patient p ON t.patient_id = p.id
JOIN device d ON t.device_id = d.id
WHERE t.optional_metadata LIKE '%elevated%'
ORDER BY t.timestamp DESC;
```

**Results**:
- Blood pressure 130/85 for Linda Miller (2026-03-13)
- Glucose 140 mg/dL for James Wilson (2026-03-12)
- Glucose 135 mg/dL for James Wilson (2026-03-15)

---

### 4. Get Active Device-Patient Assignments
```sql
SELECT p.name, d.model, d.device_type, dp.assignment_date
FROM patient p
JOIN device_patient dp ON p.id = dp.patient_id
JOIN device d ON dp.device_id = d.id
WHERE dp.assignment_end_date IS NULL
ORDER BY p.name;
```

**Result**: All 10 active device-patient assignments

---

## Telemetry Statistics from Seed Data

### Heart Rate Monitor (Device 1)
- Total Readings: 8
- Min: 68 bpm
- Max: 88 bpm
- Average: 77.1 bpm
- Normal Readings: 8/8 (100%)

### Blood Pressure Monitor (Device 2)
- Total Readings: 7
- Systolic Range: 116-130 mmHg
- Diastolic Range: 74-85 mmHg
- Normal Readings: 6/7 (85.7%)
- Elevated Readings: 1/7 (14.3%)

### Glucose Sensor (Device 3)
- Total Readings: 8
- Min: 98 mg/dL
- Max: 140 mg/dL
- Average: 119 mg/dL
- Normal Readings: 5/8 (62.5%)
- Elevated Readings: 3/8 (37.5%)

### Oxygen Sensor (Device 4)
- Total Readings: 7
- Min: 95%
- Max: 100%
- Average: 97.6%
- Normal Readings: 7/7 (100%)

---

## Key Relationships Summary

| Relationship | Type | Count | Example |
|---|---|---|---|
| Patient to Device | M:M | 10 | John Smith has Heart Rate Monitor |
| Device to Telemetry | 1:M | 30 | Heart Rate Monitor has 8 readings |
| Patient to Telemetry | 1:M | 30 | John Smith has 3 heart rate readings |
| Device Assignment Active | 1:M | 10 | All 10 assignments are ongoing |

---

## Data Integrity Rules

✅ **Foreign Key Constraints**:
- `device_patient.patient_id` → `patient.id`
- `device_patient.device_id` → `device.id`
- `telemetry.patient_id` → `patient.id`
- `telemetry.device_id` → `device.id`

✅ **Unique Constraints**:
- `patient.number_id` (must be unique)
- `device.serial_number` (must be unique)

✅ **Not Null Constraints**:
- All critical fields are NOT NULL
- Optional fields: `telemetry.optional_metadata`, `device_patient.assignment_end_date`

---

## Test Scenarios with Seed Data

### Scenario 1: Monitor Diabetic Patient
**Patient**: James Wilson (ID: 7)  
**Device**: Glucose Sensor (ID: 3)  
**Readings**: 8 records with elevated glucose levels  
**Use**: Test glucose monitoring and alert triggering

### Scenario 2: Monitor Cardiac Patient
**Patient**: John Smith (ID: 1)  
**Device**: Heart Rate Monitor (ID: 1)  
**Readings**: 3 consistent heart rate readings  
**Use**: Test continuous heart rate monitoring

### Scenario 3: Monitor Hypertensive Patient
**Patient**: Linda Miller (ID: 6)  
**Device**: Blood Pressure Monitor (ID: 2)  
**Readings**: 2 readings with 1 elevated alert  
**Use**: Test blood pressure alerts and normalization

### Scenario 4: Full Monitoring Suite
**Patient**: Patricia Brown (ID: 4)  
**Devices**: Oxygen Sensor (ID: 4)  
**Readings**: Consistent oxygen saturation data  
**Use**: Test basic sensor integration

---

## Migration Statistics

| File | Lines | Records | Purpose |
|---|---|---|---|
| V5__insert_patient_seed_data.sql | 13 | 10 | Patient registration |
| V6__insert_device_seed_data.sql | 7 | 4 | Medical device setup |
| V7__insert_device_patient_assignment_seed_data.sql | 13 | 10 | Device assignment |
| V8__insert_telemetry_seed_data.sql | 40+ | 30 | Sensor readings |
| **TOTAL** | **73+** | **54** | **Complete setup** |

---

**Generated**: 2026-03-17  
**Database**: PostgreSQL  
**Framework**: Spring Boot 3.5.11  
**ORM**: Spring Data JPA  
**Migration Tool**: Flyway
