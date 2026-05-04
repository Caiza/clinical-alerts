package com.caiza.clinical_alerts.controller;

import com.caiza.clinical_alerts.dto.telemetry.TelemetryDTO;
import com.caiza.clinical_alerts.service.TelemetryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api/telemetry")
@RequiredArgsConstructor
public class TelemetryController {

    private final TelemetryService telemetryService;
    private final Environment environment;

    @GetMapping("/config-check")
    public Map<String, Object> checkConfig() {
        Map<String, Object> config = new HashMap<>();

        config.put("hikari-max-pool-size",
                environment.getProperty("spring.datasource.hikari.maximum-pool-size"));

        config.put("tomcat-max-threads",
                environment.getProperty("server.tomcat.threads.max"));

        config.put("server-port",
                environment.getProperty("server.port"));

        return config;
    }

    @PostMapping
    @Operation(summary = "save telemetry", description = "Receives telemetry data and processes it according to defined rules.")
    public CompletableFuture<ResponseEntity<Void>> receive(@RequestBody @Valid TelemetryDTO dto){
        return CompletableFuture.supplyAsync(() -> {
            telemetryService.received(dto);
            return ResponseEntity.accepted().build();
        });

    }

    @GetMapping("/list")
    @Operation(summary = "Get all telemetry", description = "Retrieves a list of all telemetry.")
    public ResponseEntity<Page<TelemetryDTO>> getAllTelemetry(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size,
                                                              @RequestParam(defaultValue = "id") String sortBy,
                                                              @RequestParam(defaultValue = "asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TelemetryDTO> telemetryList = telemetryService.getAllTelemetry(pageable);
        return ResponseEntity.ok(telemetryList);
    }

    @GetMapping("/patientid")
    @Operation(summary = "Get telemetry by patient id", description = "Retrieves a list of telemetry filtered by patient id.")
    public ResponseEntity<List<TelemetryDTO>> getTelemetryByPatientId(Long patientId) {
        List<TelemetryDTO> telemetryList = telemetryService.getTelemetryByPatientId(patientId);
        return ResponseEntity.ok(telemetryList);
    }

}
