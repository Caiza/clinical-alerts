package com.caiza.clinical_alerts.controller;

import com.caiza.clinical_alerts.dto.DevicePatientDTO;
import com.caiza.clinical_alerts.service.DevicePatientService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@RequiredArgsConstructor
@RequestMapping("/api/device-patient")
public class DevicePatientController {

        private DevicePatientService devicePatientService;

        @PostMapping("/save")
        @Operation(summary = "Create a new Device to Patiente", description = "Create a new Device to Patiente.")
        public ResponseEntity<DevicePatientDTO> createDeviceToPatient(DevicePatientDTO devicePatientDTO) {
            DevicePatientDTO dto = devicePatientService.createDeviceToPatient(devicePatientDTO);
            return ResponseEntity.ok(dto);
        }

        @GetMapping("/getId")
        @Operation(summary = "Get Device to Patiente by ID", description = "Retrieves a Device to Patiente by their unique ID.")
        public ResponseEntity<Optional<DevicePatientDTO>> getDevicePatientById(Long id) {
            Optional<DevicePatientDTO> dto = devicePatientService.getDevicePatientById(id);
            return ResponseEntity.ok(dto);
        }

        @GetMapping("/getAll")
        @Operation(summary = "Get all Device to Patiente", description = "Retrieves a list of all Device to Patiente.")
        public ResponseEntity<DevicePatientDTO> getAllDevicePatient() {
            DevicePatientDTO dto = devicePatientService.getAllDevicePatient();
            return ResponseEntity.ok(dto);
        }
}
