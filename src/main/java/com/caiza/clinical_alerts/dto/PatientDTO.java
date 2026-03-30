package com.caiza.clinical_alerts.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long id;
    @NotBlank(message="Name is required")
    @Size(max = 100, message="Name must be less than 100 characters")
    private String name;
    @NotNull
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @NotBlank(message="Gender is required")
    @Pattern(regexp = "Male|Female|Other", message="Gender must be male, famale or other")
    private String gender;
    @NotNull(message="Status is required")
    private Boolean status;
    @NotNull(message = "Number ID is required")
    @Positive(message = "Number ID must be a positive integer")
    private Integer numberId;

}