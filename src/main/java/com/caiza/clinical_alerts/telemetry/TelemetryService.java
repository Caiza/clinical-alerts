package com.caiza.clinical_alerts.telemetry;


import com.caiza.clinical_alerts.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.caiza.clinical_alerts.telemetry.TelemetryMapper.toTelemetry;
import static com.caiza.clinical_alerts.telemetry.TelemetryMapper.toTelemetryDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class TelemetryService {

    TelemetryRepository telemetryRepository;

    public TelemetryDTO createTelemetryFromDTO(TelemetryDTO telemetryDTO) {

        Telemetry telemetry =  toTelemetry(telemetryDTO);
        Optional<Telemetry> response = telemetryRepository.findById(telemetry.getId());
        if(response.isPresent()){
            throw new BusinessException("Telemetry with the same id already exists");
        }
        Telemetry savedTelemetry = telemetryRepository.save(telemetry);
        return toTelemetryDTO(savedTelemetry);
    }

    public List<TelemetryDTO> getAllTelemetry() {
        List<Telemetry> telemetryList = telemetryRepository.findAll();
        if(telemetryList.isEmpty()){
            throw new BusinessException("No telemetry found");
        }
        return telemetryList.stream().map(TelemetryMapper::toTelemetryDTO).toList();
    }

    public List<TelemetryDTO> getTelemetryByPatientId(Long patientId) {
        List<Telemetry> telemetryList = telemetryRepository.findByPatientId(patientId);
        if(telemetryList.isEmpty()){
            throw new BusinessException("No telemetry found for the patient id: " + patientId);
        }
        return telemetryList.stream().map(TelemetryMapper::toTelemetryDTO).toList();
    }
}
