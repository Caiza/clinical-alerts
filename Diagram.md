@startuml actor Client participant "Rest API" as Controller participant "Service Layer" as Service participant "JPA Repository" as Repository database "PostgreSQL Database" as DB participant "Patient DTO" as patientDTO participant "Patient Entity" as patientEntity participant "Mapper" as Mapper

Client -> Controller : Post /patients/save with patientDTO
Controller -> Service : createPatient(patientDTO)
Service -> Mapper : toEntity(patientDTO)
Mapper -> Service : patientEntity
Service -> Repository : save(patientEntity)
Repository -> DB : INSERT INTO patientEntity (...)
DB --> Repository : patientEntity with generated ID
Repository -> Service : patientEntity with generated ID
Service -> Mapper : toDTO(patientEntity)
Mapper -> Service : patientDTO
Service -> Controller : patientDTO
Controller -> Client : HTTP 201 Created with patientDTO


@enduml