package com.caiza.clinical_alerts.patient;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientControler {

    @Autowired
    private PatientService patientService;

    @Operation(summary = "Create a new patient", description = "Creates a new patient with the provided details.")
    @PostMapping("/save")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody @Valid PatientDTO patientDTO) {
        Patient saved = patientService.create(patientDTO);
        PatientDTO response = PatientMapper.toDTO(saved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    

}
