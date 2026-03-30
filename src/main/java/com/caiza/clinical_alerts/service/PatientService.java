package com.caiza.clinical_alerts.service;

import com.caiza.clinical_alerts.dto.PatientDTO;
import com.caiza.clinical_alerts.exception.BusinessException;
import com.caiza.clinical_alerts.exception.ResourceNotFoundException;
import com.caiza.clinical_alerts.mapper.PatientMapper;
import com.caiza.clinical_alerts.model.Patient;
import com.caiza.clinical_alerts.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientService {

    private PatientRepository patientRepository;

    public PatientDTO create(PatientDTO patientDTO){
        Patient patient = PatientMapper.toEntity(patientDTO);
        if(patientRepository.existsByNumberId(patient.getNumberId())){
            throw new BusinessException("Patient with numberId " + patient.getNumberId() + " already exists.");
        }
        Patient saved = patientRepository.save(patient);
        PatientDTO response = PatientMapper.toDTO(saved);
        return response;
    }

    public Page<PatientDTO> findAll(Pageable pageable){
        Page<Patient> patients = patientRepository.findAll(pageable);
        if(patients.isEmpty()){
            throw new ResourceNotFoundException("No patients found.");
        }
        Page<PatientDTO> response = patients.map(PatientMapper::toDTO);
        return response;
    }

    public PatientDTO findById(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Patient with id " + id + " not found."));
        PatientDTO response = PatientMapper.toDTO(patient);
        return response;
    }

    public Page<PatientDTO> findByStatus(Boolean status, Pageable pageable){
        Page<Patient> patientsList = patientRepository.findByStatus(status, pageable);
        if(patientsList.isEmpty()){
            throw new ResourceNotFoundException("No patients found with status " + status + ".");
        }
        Page<PatientDTO> response = patientsList.map(PatientMapper::toDTO);
        return response;
    }

    public void delete(Long id){
        patientRepository.deleteById(id);
    }

    public PatientDTO updatePatient(Long id, PatientDTO patientDTO){
        Patient newPatient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        newPatient.setName(patientDTO.getName());
        newPatient.setGender(patientDTO.getGender());
        newPatient.setStatus(patientDTO.getStatus());
        newPatient.setDateOfBirth(patientDTO.getDateOfBirth());
        Patient updatePatient =  patientRepository.save(newPatient);
        return PatientMapper.toDTO(updatePatient);

    }




}
