package com.caiza.clinical_alerts.dto;

import com.caiza.clinical_alerts.model.DeviceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO {

    Long id;
    @NotNull
    @Positive(message = "Serial number must be a positive integer")
    Integer serialNumber;
    @NotBlank
    String model;
    @NotBlank
    String manufacture;
    @NotNull
    Boolean isActive;
    @NotNull
    @Enumerated(EnumType.STRING)
    DeviceType deviceType;

}
