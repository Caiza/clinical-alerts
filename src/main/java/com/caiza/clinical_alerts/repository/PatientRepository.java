package com.caiza.clinical_alerts.repository;

import com.caiza.clinical_alerts.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findById(Long id);
    Boolean existsByNumberId(Integer numberId);
    Page<Patient> findByStatus(Boolean status, Pageable pageable);
    Page<Patient> findAll(Pageable pageable);
}
