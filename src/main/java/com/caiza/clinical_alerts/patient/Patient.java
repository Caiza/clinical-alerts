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
    @Column(nullable = false)
    private String name;
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private Boolean status;
    @Column(name = "number_id", unique = true, nullable = false)
    private Integer numberId;

}