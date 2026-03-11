package com.caiza.clinical_alerts.patient;

import com.caiza.clinical_alerts.exception.BusinessException;
import com.caiza.clinical_alerts.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient create(PatientDTO patientDTO){
        Patient patient = PatientMapper.toEntity(patientDTO);
        if(patientRepository.existsByNumberId(patient.getNumberId())){
            throw new BusinessException("Patient with numberId " + patient.getNumberId() + " already exists.");
        }
        return patientRepository.save(patient);
    }

    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    public Patient findById(Long id){
        return patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient with id " + id + " not found."));
    }

    public void delete(Long id){
        patientRepository.deleteById(id);
    }

}
