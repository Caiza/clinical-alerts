package com.caiza.clinical_alerts.patient;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


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

    @Operation(summary = "Get all patients", description = "Retrieves a list of all patients.")
    @GetMapping("/list")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<Patient> patientList = patientService.findAll();
        List<PatientDTO> response = PatientMapper.toListDTO(patientList);
        return ResponseEntity.ok(response);
    }

    @Operation(summary= "Get patient by ID", description = "Retrives a patient by their unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id){
        Patient patient = patientService.findById(id);
        PatientDTO response = PatientMapper.toDTO(patient);
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Get patients by status", description = "Retrieves a list of patients filtered by their status (active/inactive).")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PatientDTO>> getPatientByStatus(@PathVariable Boolean status, @RequestParam int page,
                                                               @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        List<Patient> patientList = patientService.findByStatus(status, pageable);
        List<PatientDTO> response = PatientMapper.toListDTO(patientList);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient", description = "Deletes a patient by their unique ID.")
    public ResponseEntity<PatientDTO> deletePatient(@PathVariable Long id){
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
    

}
