package com.caiza.clinical_alerts.telemetry;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.caiza.clinical_alerts.telemetry.TelemetryMapper.toTelemetryDTO;

@RestController
@RequestMapping("/api/telemetry")
@RequiredArgsConstructor
public class TelemetryControler {

    private TelemetryService telemetryService;

    @PostMapping("/save")
    @Operation(summary = "Create a new telemetry", description = "Creates a new telemetry with the provided details.")
    public ResponseEntity<TelemetryDTO> saveTelemetry(TelemetryDTO telemetryDTO) {
        TelemetryDTO response = telemetryService.createTelemetryFromDTO(telemetryDTO);
        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<Void> receive(@RequestBody @Valid TelemetryDTO dto){
        telemetryService.received(dto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/list")
    @Operation(summary = "Get all telemetry", description = "Retrieves a list of all telemetry.")
    public ResponseEntity<List<TelemetryDTO>> getAllTelemetry() {
        List<TelemetryDTO> telemetryList = telemetryService.getAllTelemetry();
        return ResponseEntity.ok(telemetryList);
    }

    @GetMapping("/patientid")
    @Operation(summary = "Get telemetry by patient id", description = "Retrieves a list of telemetry filtered by patient id.")
    public ResponseEntity<List<TelemetryDTO>> getTelemetryByPatientId(Long patientId) {
        List<TelemetryDTO> telemetryList = telemetryService.getTelemetryByPatientId(patientId);
        return ResponseEntity.ok(telemetryList);
    }

}
