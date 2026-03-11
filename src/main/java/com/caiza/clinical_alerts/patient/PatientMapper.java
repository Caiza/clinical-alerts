package com.caiza.clinical_alerts.patient;

public class PatientMapper {

    public static PatientDTO toDTO(Patient patient) {
        return new PatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getDateOfbirth(),
                patient.getGender(),
                patient.getStatus(),
                patient.getNumberId()
        );
    }

    public static Patient toEntity(PatientDTO patientDTO) {
        return new Patient(
                patientDTO.getId(),
                patientDTO.getName(),
                patientDTO.getDateOfbirth(),
                patientDTO.getGender(),
                patientDTO.getStatus(),
                patientDTO.getNumberId()
        );
    }
}
