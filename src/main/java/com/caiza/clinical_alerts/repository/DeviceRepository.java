package com.caiza.clinical_alerts.repository;

import com.caiza.clinical_alerts.model.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Page<Device> findAll(Pageable pageable);
    Device findBySerialNumber(Integer serialNumber);

}
