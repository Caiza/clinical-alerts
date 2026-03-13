package com.caiza.clinical_alerts.patient;

import com.caiza.clinical_alerts.exception.BusinessException;
import com.caiza.clinical_alerts.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<Patient> findAll(Pageable pageable){
        Page<Patient> patients = patientRepository.findAll(pageable);
        if(patients.isEmpty()){
            throw new ResourceNotFoundException("No patients found.");
        }
        return patients;
    }

    public Patient findById(Long id){
        return patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient with id " + id + " not found."));
    }

    public Page<Patient> findByStatus(Boolean status, Pageable pageable){
        Page<Patient> patients = patientRepository.findByStatus(status, pageable);
        if(patients.isEmpty()){
            throw new ResourceNotFoundException("No patients found with status " + status + ".");
        }
        return patients;
    }

    public void delete(Long id){
        patientRepository.deleteById(id);
    }

    public Patient updatePatient(Long id, PatientDTO patientDTO){
        Patient patient = findById(id);
             Patient newPatient = PatientMapper.toEntity(patientDTO);
        patient.setName(newPatient.getName());
        patient.setGender(newPatient.getGender());
        patient.setStatus(newPatient.getStatus());
        patient.setDateOfBirth(newPatient.getDateOfBirth());
        return patientRepository.save(patient);

    }




}
