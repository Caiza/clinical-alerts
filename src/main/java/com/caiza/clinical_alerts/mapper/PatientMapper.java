package com.caiza.clinical_alerts.mapper;

import com.caiza.clinical_alerts.dto.PatientDTO;
import com.caiza.clinical_alerts.model.Patient;

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

}
