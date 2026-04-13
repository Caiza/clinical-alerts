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