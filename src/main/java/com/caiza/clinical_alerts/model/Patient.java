package com.caiza.clinical_alerts.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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