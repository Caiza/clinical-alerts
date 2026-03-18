package com.caiza.clinical_alerts.patient;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientControler {

    private PatientService patientService;

    @Operation(summary = "Create a new patient", description = "Creates a new patient with the provided details.")
    @PostMapping("/save")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody @Valid PatientDTO patientDTO) {
        PatientDTO saved = patientService.create(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //I implement pagination using Pageable and page and size as query parameters to make the endpoint faster more flexible, and scalable
    @Operation(summary = "Get all patients", description = "Retrieves a list of all patients.")
    @GetMapping("/list")
    public ResponseEntity<Page<PatientDTO>> getAllPatients(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size,
                                                           @RequestParam(defaultValue = "id") String sortBy,
                                                           @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<PatientDTO> patientList = patientService.findAll(pageable);
        return ResponseEntity.ok(patientList);
    }

    @Operation(summary = "Get patient by ID", description = "Retrives a patient by their unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patient = patientService.findById(id);
        return ResponseEntity.ok(patient);
    }

    @Operation(summary = "Get patients by status", description = "Retrieves a list of patients filtered by their status (active/inactive).")
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<PatientDTO>> getPatientByStatus(@PathVariable Boolean status, @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size,
                                                               @RequestParam(defaultValue = "id") String sortBy,
                                                               @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<PatientDTO> patientList = patientService.findByStatus(status, pageable);
        return ResponseEntity.ok(patientList);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient", description = "Deletes a patient by their unique ID.")
    public ResponseEntity<PatientDTO> deletePatient(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a patient", description = "Updates the details of an existing patient by their unique ID.")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody @Valid PatientDTO patientDTO) {
        PatientDTO patient = patientService.updatePatient(id, patientDTO);
        return ResponseEntity.ok(patient);
    }


}
