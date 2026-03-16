package com.caiza.clinical_alerts.device;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "device")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name= "serial_number", unique = true, nullable = false)
    Integer serialNumber;
    @Column(nullable = false)
    String model;
    @Column(nullable = false)
    String manufacture;
    @Column(name= "is_active", nullable = false)
    Boolean isActive = false;
    @Enumerated(EnumType.STRING)
    @Column(name= "device_type", nullable = false)
    DeviceType deviceType;

}
