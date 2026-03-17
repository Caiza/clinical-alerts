package com.caiza.clinical_alerts.telemetry;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.caiza.clinical_alerts.telemetry.TelemetryMapper.toTelemetry;

@Service
public class TelemetryService {

    @Autowired
    TelemetryRepository telemetryRepository;

    public Telemetry createTelemetryFromDTO(TelemetryDTO telemetryDTO) {

        Telemetry telemetry =  toTelemetry(telemetryDTO);
        Optional<Telemetry> response = telemetryRepository.findById(telemetry.getId());
        if(response.isPresent()){
            throw new RuntimeException("Telemetry with the same id already exists");
        }
        Telemetry savedTelemetry = (Telemetry) telemetryRepository.save(telemetry);
        return savedTelemetry;
    }

    public List<Telemetry> getAllTelemetry() {
        List<Telemetry> telemetryList = telemetryRepository.findAll();
        if(telemetryList.isEmpty()){
            throw new RuntimeException("No telemetry found");
        }
        return telemetryList;
    }

    public List<Telemetry> getTelemetryByPatientId(Long patientId) {
        List<Telemetry> telemetryList = telemetryRepository.findByPatientId(patientId);
        if(telemetryList.isEmpty()){
            throw new RuntimeException("No telemetry found for the patient id: " + patientId);
        }
        return telemetryList;
    }
}
