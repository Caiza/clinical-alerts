Clinical Alerts API

Backend system for managing patient monitoring data and clinical alerts.
The API allows registering patients, managing clinical information, and storing monitoring data for healthcare applications.

## Features
- Patient management API for registering and retrieving patient information.
- Restful endpoints
- Database migrations with Flyway
- Centralized exception handling for better error management.
- API documentation with Swagger for easy testing and integration.
- DTO pattern for resquest/response handling

## Tech Stack
- Java
- Spring Boot
- PostgreSQL
- Flyway
- Swagger
- Maven

## Architecture
The application follows a layered architecture with the following components:

Controller -> Service -> Repository -> Database

- **Controller Layer**: Handles HTTP requests and responses.
- **Service Layer**: Contains business logic and interacts with the repository layer.
- **Repository Layer**: Manages database interactions using Spring Data JPA.
- **Model Layer**: Contains entity classes representing the database tables.
- **DTO Layer**: Contains Data Transfer Objects for transferring data between layers.
- **Exception Handling**: Custom exceptions and global exception handling for better error management.
- **Database - PostgresSQL.
- Database migrations are managed using Flyway, ensuring smooth schema evolution.

## API Endpoints
- `POST /api/patients`: Register a new patient.
- `GET /api/patients/{id}`: Retrieve patient information by ID.
- `POST /api/monitoring-data`: Store monitoring data for a patient.
- `GET /api/monitoring-data/{patientId}`: Retrieve monitoring data for a
    patient by ID.

## API Documentation
Swagger UI is available at: http://localhost:8081/swagger-ui/index.html

## How to Run the Project
1- Clone the repository:

   git clone https://github.com/Caiza/clinical-alerts.git
   cd clinical-alerts-api

2- Build the project using Maven:
    
    mvn clean install
    ```
3- Configure the database:
   - Update the `application.properties` file with your PostgreSQL database credentials.
   4- Run the application:
    ```
    mvn spring-boot:run
    ```
API will be available at: http://localhost:8081

## Example Endpoint
### Register a new patient
- **Endpoint**: `POST http://localhost:8081/patients/save`
- **Request Body**:
json
{
  "name": "John Doe",
  "dateOfBirth": "1990-01-01",
  "gender": "Male",
  "status": true,
  "numberId": 1001
}


@startuml
actor Client
participant "Rest API" as API
participant "Service Layer" as Service
participant "JPA Repository" as Repository
database "PostgreSQL Database" as DB

Client -> API : Post /patients/save
API -> Service : createPatient(patientDTO)
Service -> Repository : save(patient)
Repository -> DB : INSERT INTO patient (...)
DB --> Repository : Patient data
Repository --> Service : asved patient
Service --> API : patient response
API --> Client : HTTP 201 Created



@enduml

