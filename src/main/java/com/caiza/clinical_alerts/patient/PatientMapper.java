package com.caiza.clinical_alerts.patient;

import java.util.List;

public class PatientMapper {

    public static PatientDTO toDTO(Patient patient) {
        return new PatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getDateOfBirth(),
                patient.getGender(),
                patient.getStatus(),
                patient.getNumberId()
        );
    }

    public static Patient toEntity(PatientDTO patientDTO) {
        return new Patient(
                patientDTO.getId(),
                patientDTO.getName(),
                patientDTO.getDateOfBirth(),
                patientDTO.getGender(),
                patientDTO.getStatus(),
                patientDTO.getNumberId()
        );
    }
    public static List<PatientDTO> toListDTO(List<Patient> patientList){
        return patientList.stream().map(PatientMapper::toDTO).toList();
    }
}
