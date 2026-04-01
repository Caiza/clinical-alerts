# Clinical Alerts API

## Overview

**Clinical Alerts** is a real-time healthcare monitoring system that processes patient telemetry data and generates clinical alerts based on predefined risk rules. The system is built with a **modern event-driven architecture** using Apache Kafka for asynchronous telemetry processing, Spring Boot for REST API management, and PostgreSQL for persistent storage.

The API enables healthcare providers to:
- Register and manage patient information
- Track medical devices assigned to patients
- Receive and process real-time telemetry data (heart rate, blood pressure, glucose, oxygen levels, etc.)
- Generate automatic alerts when measurements indicate clinical risk
- Query historical monitoring data and alerts

---

## Core Features

- **Patient Management API**: Register, retrieve, list, and manage patient information with pagination support
- **Device Management**: Track medical devices and their assignments to patients
- **Real-Time Telemetry Processing**: Receive telemetry events and process them via Kafka topics
- **Intelligent Alert Generation**: Evaluate telemetry data against clinical rules and automatically generate alerts when risk levels exceed normal thresholds
- **RESTful Endpoints**: Complete REST API with comprehensive Swagger documentation
- **Data Validation**: Built-in request validation using Jakarta Bean Validation
- **Exception Handling**: Centralized error handling with custom business and resource exceptions
- **Database Migrations**: Automatic schema management using Flyway
- **API Documentation**: Interactive Swagger UI for testing all endpoints
- **DTO Pattern**: Clean separation of data transfer objects from persistent entities
- **Spring Security Integration**: Framework support for future authentication/authorization

---

## Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Language** | Java | 21 |
| **Framework** | Spring Boot | 3.5.11 |
| **Database** | PostgreSQL | Latest (via JDBC driver) |
| **Message Broker** | Apache Kafka | Latest (Spring Kafka) |
| **ORM** | Spring Data JPA with Hibernate | Latest |
| **API Documentation** | Springdoc OpenAPI (Swagger 3) | Latest |
| **Data Validation** | Jakarta Bean Validation | Latest |
| **Migrations** | Flyway | Latest |
| **Build Tool** | Maven | 3.x |
| **Dependency Injection** | Lombok | Latest |
| **Monitoring** | Spring Boot Actuator | 3.5.11 |

---

## Architecture

The application follows a **layered architecture** with clear separation of concerns:

```
┌─────────────────────────────────────────────────────────┐
│                   Controller Layer                       │
│        (HTTP Request/Response Handling)                  │
│  PatientController  |  DeviceController  |  etc.         │
└──────────────────────┬──────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────┐
│                   Service Layer                         │
│      (Business Logic & Rule Evaluation)                 │
│  PatientService  |  AlertService  |  TelemetryService  │
└──────────────────────┬──────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────┐
│                Repository Layer                         │
│        (Database Access via Spring Data JPA)            │
│  PatientRepository  |  AlertRepository  |  etc.         │
└──────────────────────┬──────────────────────────────────┘
                       │
┌──────────────────────▼──────────────────────────────────┐
│              PostgreSQL Database                        │
│     (Patient, Device, Telemetry, Alert Tables)          │
└─────────────────────────────────────────────────────────┘
```

### Kafka Event-Driven Flow

Telemetry data follows an **asynchronous event-driven pipeline**:

```
Telemetry Input
     │
     ▼
┌────────────────────────────┐
│ KafkaTelemetryEventPublisher│  (Publishes to Kafka topic)
└────────────┬───────────────┘
             │
             ▼
        Kafka Topic
     "telemetry-topic"
             │
             ▼
┌────────────────────────────────────┐
│      TelemetryConsumer             │  (Kafka Consumer)
│  (Listens on telemetry-topic)      │
└────────────┬───────────────────────┘
             │
             ▼
┌────────────────────────────────────┐
│   TelemetryRuleFactory             │  (Strategy Pattern)
│  (Selects appropriate rule)        │
└────────────┬───────────────────────┘
             │
             ▼
┌────────────────────────────────────┐
│  TelemetryRulesStrategy            │  (Evaluates risk level)
│  (Heart Rate, BP, Glucose rules)   │
└────────────┬───────────────────────┘
             │
             ▼
      Risk Level Evaluation
             │
        ┌────┴────┐
        │          │
      NORMAL    ABNORMAL
        │          │
        │          ▼
        │   ┌──────────────────────────┐
        │   │   AlertService           │
        │   │   (Generate Alert)       │
        │   └──────────┬───────────────┘
        │              │
        │              ▼
        │       ┌──────────────────┐
        │       │  AlertRepository │
        │       │  (Persist Alert) │
        │       └──────────────────┘
        │
        └─────────► Log Event
```

### Layer Responsibilities

| Layer | Responsibility | Examples |
|-------|---------------|---------| 
| **Controller** | Handle HTTP requests/responses, validate input, return appropriate status codes | PatientController, DeviceController, TelemetryController |
| **Service** | Business logic, transaction management, interacts with repositories | PatientService, AlertService, TelemetryService |
| **Repository** | CRUD operations and database queries | PatientRepository, DeviceRepository, AlertRepository |
| **Model** | JPA entities representing database tables | Patient, Device, Telemetry, Alert |
| **DTO** | Data transfer objects for API request/response | PatientDTO, DeviceDTO, TelemetryDTO |
| **Exception Handling** | Custom exceptions and global error handling | BusinessException, ResourceNotFoundException |

---

## Data Model

### Entity Relationships

```
┌──────────────┐         ┌──────────────┐
│   PATIENT    │         │    DEVICE    │
├──────────────┤         ├──────────────┤
│ id (PK)      │────┐    │ id (PK)      │
│ name         │    │    │ type         │
│ dateOfBirth  │    │    │ model        │
│ gender       │    │    │              │
│ status       │    │    │              │
│ numberId (U) │    │    └──────┬───────┘
└──────────────┘    │           │
                    │      ┌────▼──────────┐
                    │      │ DEVICE_PATIENT│
                    │      ├───────────────┤
                    │      │ id (PK)       │
                    │      │ patientId (FK)├─────┐
                    │      │ deviceId (FK) ├────┬┘
                    │      │ assignedDate  │    │
                    │      └───────────────┘    │
                    │                          │
                    │      ┌──────────────────┤
                    │      │    TELEMETRY    │
                    │      ├──────────────────┤
                    └─────►│ id (PK)         │
                           │ patientId (FK)  │
                           │ deviceId (FK)   │
                           │ type            │
                           │ measuredValue   │
                           │ timestamp       │
                           └────────┬────────┘
                                    │
                                    │
                           ┌────────▼─────────┐
                           │     ALERT        │
                           ├──────────────────┤
                           │ id (PK)          │
                           │ patientId (FK)   │
                           │ deviceId (FK)    │
                           │ signalType       │
                           │ measuredValue    │
                           │ riskLevel        │
                           │ createdAt        │
                           └──────────────────┘
```

### Key Entities

- **Patient**: Core entity representing a patient with demographics (name, DOB, gender, unique ID)
- **Device**: Medical device types tracked by the system (heart rate monitor, BP monitor, glucose sensor, etc.)
- **DevicePatient**: Junction table managing many-to-many relationships between patients and devices
- **Telemetry**: Raw measurement data received from devices (value, timestamp, unit, metadata)
- **Alert**: Generated alerts created when telemetry evaluates to abnormal risk levels

---

## REST API Endpoints

### Patient Management (`/api/patients`)

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| **POST** | `/api/patients/save` | Register a new patient | 201 Created |
| **GET** | `/api/patients/list` | List all patients (paginated) | 200 OK |
| **GET** | `/api/patients/{id}` | Get patient by ID | 200 OK |
| **GET** | `/api/patients/status` | Get patients by status (paginated) | 200 OK |
| **DELETE** | `/api/patients/{id}` | Delete patient by ID | 204 No Content |

**Query Parameters for Pagination:**
- `page` (default: 0) - Page number
- `size` (default: 10) - Records per page
- `sortBy` (default: id) - Sort field
- `direction` (default: asc) - Sort direction (asc/desc)

### Device Management (`/api/devices`)

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| **POST** | `/api/devices` | Create a new device | 201 Created |
| **GET** | `/api/devices` | List all devices | 200 OK |
| **GET** | `/api/devices/{id}` | Get device by ID | 200 OK |
| **PUT** | `/api/devices/{id}` | Update device | 200 OK |
| **DELETE** | `/api/devices/{id}` | Delete device | 204 No Content |

### Device-Patient Assignment (`/api/device-patients`)

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| **POST** | `/api/device-patients` | Assign device to patient | 201 Created |
| **GET** | `/api/device-patients` | List all assignments | 200 OK |
| **GET** | `/api/device-patients/{patientId}` | Get devices for patient | 200 OK |
| **DELETE** | `/api/device-patients/{id}` | Remove assignment | 204 No Content |

### Telemetry Processing (`/api/telemetry`)

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| **POST** | `/api/telemetry` | Receive and process telemetry data | 202 Accepted |
| **GET** | `/api/telemetry/list` | Get all telemetry records | 200 OK |
| **GET** | `/api/telemetry/patientid` | Get telemetry by patient ID | 200 OK |

---

## How to Persist a Patient: End-to-End Flow

### 1. API Request (Controller Layer)

**Endpoint**: `POST /api/patients/save`

**Request Body**:
```json
{
  "name": "John Doe",
  "dateOfBirth": "1990-01-15",
  "gender": "Male",
  "status": true,
  "numberId": 1001
}
```

### 2. Validation & Controller Processing

The `PatientController` receives the request:
- Validates the `PatientDTO` using Jakarta Bean Validation annotations
- Validates constraints: name not blank (max 100 chars), date of birth in past, gender pattern matching, positive number ID
- Calls `PatientService.create(patientDTO)`

### 3. Service Layer (Business Logic)

The `PatientService` processes the DTO:
```
PatientService.create(PatientDTO)
  ├─ Convert DTO → Entity using PatientMapper.toEntity()
  ├─ Check if numberId already exists in database
  │   └─ If exists: throw BusinessException
  ├─ Invoke patientRepository.save(patient)
  └─ Convert saved Entity → DTO using PatientMapper.toDTO()
     └─ Return PatientDTO response
```

**Key Features:**
- `@Transactional` annotation ensures atomicity
- Duplicate number ID prevention
- Automatic entity-to-DTO conversion

### 4. Repository & Database Layer

The `PatientRepository` (Spring Data JPA) executes:
- SQL: `INSERT INTO patient (name, date_of_birth, gender, status, number_id) VALUES (...)`
- Returns the persisted entity with auto-generated `id`

### 5. Response to Client

**Status Code**: `201 Created`

**Response Body**:
```json
{
  "id": 1,
  "name": "John Doe",
  "dateOfBirth": "1990-01-15",
  "gender": "Male",
  "status": true,
  "numberId": 1001
}
```

### Code Flow Diagram

```
HTTP Request (PatientDTO)
    │
    ▼
PatientController.createPatient()
    │
    ├─ @Valid PatientDTO validation
    │  (Bean Validation constraints)
    │
    ▼
PatientService.create(PatientDTO)
    │
    ├─ PatientMapper.toEntity(DTO)
    │  (DTO → Patient Entity)
    │
    ├─ Check: numberId duplicate?
    │  YES: throw BusinessException
    │  NO:  continue
    │
    ▼
PatientRepository.save(Patient)
    │
    ├─ Generate SQL INSERT statement
    │
    ▼
PostgreSQL Database
    │
    ├─ ACID transaction committed
    │ (auto-increment ID generated)
    │
    ▼
PatientRepository returns saved Patient
    │
    ▼
PatientService.PatientMapper.toDTO(Patient)
    │
    └─ Entity → DTO conversion
    │
    ▼
ResponseEntity<PatientDTO>
Status: 201 Created
    │
    ▼
HTTP Response (PatientDTO + ID)
```

---

## How to Run the Project

### Prerequisites

Ensure you have installed:
- **Java 21** or higher (`java -version`)
- **Apache Maven 3.6+** (`mvn -version`)
- **PostgreSQL 12+** (running and accessible)
- **Apache Kafka 3.x+** (running on localhost:9092)

### Step 1: Clone the Repository

```bash
git clone https://github.com/Caiza/clinical-alerts.git
cd clinical-alerts
```

### Step 2: Configure the Database

Update `src/main/resources/application.yaml` with your PostgreSQL credentials:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/clinical
    username: clinical
    password: clinical
  jpa:
    hibernate:
      ddl-auto: validate  # Flyway manages schema
```

**Create the database**:
```sql
CREATE DATABASE clinical;
```

### Step 3: Configure Kafka

Ensure Kafka is running on `localhost:9092`:

```bash
# Start Kafka broker (if not already running)
kafka-server-start.sh config/server.properties
```

The application will automatically create the required Kafka topic `telemetry-topic`.

### Step 4: Build the Project

```bash
mvn clean install
```

This command:
- Cleans previous builds
- Downloads dependencies
- Runs unit tests
- Builds the JAR artifact

### Step 5: Run the Application
```bash
mvn spring-boot:run

```

### Step 6: Create the telemetry data

**Status Code**: `201 Created`

**Endpoint**: `POST http://localhost:8081/api/telemetry`

**Request Body**:
```json
{
    "deviceId": 1,
    "patientId": 1,
    "type": "OXYGEN_SATURATION",
    "timestamp": "2026-03-30T10:00:00Z",
    "optionalMetadata": "Patient resting after exercise",
    "mensuredValue": 30,
    "unit": "bpm"
}
```

## How to Run the K6

### Prerequisites

Ensure you have installed:
- **K6**
```bash
choco install k6 (version v1.6.1)
```

### RUN the K6 test
```bash
  k6 run --out web-dasboard k6-tests/telemetry_test.js
```
### Open web dashboard
```bash
  k6 dashboard k6-tests/telemetry_test.js
```

@startuml actor Client participant "Rest API" as Controller participant "Service Layer" as Service participant "JPA Repository" as Repository database "PostgreSQL Database" as DB

Client -> Controller : Post /patients/save 
Controller -> Service : createPatient(patientDTO) 
Service -> Repository : save(patient) 
Repository -> DB : INSERT INTO patient (...) 
DB --> Repository : Patient data 
Repository --> Service : saved patient 
Service --> API : patient response 
API --> Client : HTTP 201 Created

@enduml
