package com.caiza.clinical_alerts.patient;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public Patient create(PatientDTO patientDTO){
        Patient patient = PatientMapper.toEntity(patientDTO);
        if(patientRepository.existsByNumberId(patient.getNumberId())){
            throw new IllegalArgumentException("Patient with numberId " + patient.getNumberId() + " already exists.");
        }
        return patientRepository.save(patient);
    }

    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    public Patient findById(Long id){
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient with id " + id + " not found."));
    }

    public void delete(Long id){
        patientRepository.deleteById(id);
    }

}
