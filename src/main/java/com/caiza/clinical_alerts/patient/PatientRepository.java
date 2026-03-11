package com.caiza.clinical_alerts.patient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByName(String name);
    List<Patient> findById(String id);
    Boolean existsByNumberId(Integer numberId);
}
