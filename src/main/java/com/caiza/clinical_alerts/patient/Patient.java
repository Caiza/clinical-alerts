package com.caiza.clinical_alerts.patient;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "patient")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @NotNull
    private String gender;
    @NotNull
    private Boolean status;
    @NotNull
    @Column(name = "number_id", unique = true)
    private Integer numberId;

}