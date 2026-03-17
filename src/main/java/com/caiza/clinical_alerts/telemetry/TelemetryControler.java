package com.caiza.clinical_alerts.telemetry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

import static com.caiza.clinical_alerts.telemetry.TelemetryMapper.toTelemetryDTO;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/telemetry")
public class TelemetryControler {

    @Autowired
    private TelemetryService telemetryService;

    @PostMapping("/save")
    public ResponseEntity<TelemetryDTO> saveTelemetry(TelemetryDTO telemetryDTO) {

        Telemetry telemetry = telemetryService.createTelemetryFromDTO(telemetryDTO);
        TelemetryDTO response = toTelemetryDTO(telemetry);
        return ok(response);

    }
    @GetMapping("/list")
    public ResponseEntity<List<TelemetryDTO>> getAllTelemetry() {
        List<Telemetry> telemetryList = telemetryService.getAllTelemetry();
        return ResponseEntity.ok(telemetryList.stream().map(TelemetryMapper::toTelemetryDTO).toList());
    }

    @GetMapping("/patientid")
    public ResponseEntity<List<TelemetryDTO>> getTelemetryByPatientId(Long patientId) {
        List<Telemetry> telemetryList = telemetryService.getTelemetryByPatientId(patientId);
        return ResponseEntity.ok(telemetryList.stream().map(TelemetryMapper::toTelemetryDTO).toList());
    }

}
