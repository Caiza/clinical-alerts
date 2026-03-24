package com.caiza.clinical_alerts.patientDevice;

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

        @PostMapping("/device-to-patient")
        public ResponseEntity<DevicePatientDTO> createDeviceToPatient(DevicePatientDTO devicePatientDTO) {
            DevicePatientDTO dto = devicePatientService.createDeviceToPatient(devicePatientDTO);
            return ResponseEntity.ok(dto);
        }

        @GetMapping("/getId")
        public ResponseEntity<Optional<DevicePatientDTO>> getDevicePatientById(Long id) {
            Optional<DevicePatientDTO> dto = devicePatientService.getDevicePatientById(id);
            return ResponseEntity.ok(dto);
        }

        @GetMapping("/getAll")
        public ResponseEntity<DevicePatientDTO> getAllDevicePatient() {
            DevicePatientDTO dto = devicePatientService.getAllDevicePatient();
            return ResponseEntity.ok(dto);
        }
}
