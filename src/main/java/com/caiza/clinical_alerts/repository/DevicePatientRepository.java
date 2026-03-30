package com.caiza.clinical_alerts.repository;

import com.caiza.clinical_alerts.model.DevicePatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicePatientRepository extends JpaRepository<DevicePatient, Long> {


}
