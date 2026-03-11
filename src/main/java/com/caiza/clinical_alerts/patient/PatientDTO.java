package com.caiza.clinical_alerts.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long id;
    private String name;
    private LocalDate dateOfbirth;
    private String gender;
    private Boolean status;
    private Integer numberId;

}