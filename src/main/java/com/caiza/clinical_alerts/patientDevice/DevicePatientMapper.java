package com.caiza.clinical_alerts.patientDevice;

public class DevicePatientMapper {

    public static DevicePatient toDevicePatient(DevicePatientDTO dto) {
        DevicePatient devicePatient = new DevicePatient();
        devicePatient.setDevice(dto.getDevice());
        devicePatient.setAssignmentDate(dto.getAssignmentDate());
        devicePatient.setAssignmentEndDate(dto.getAssignmentEndDate());
        return devicePatient;
    }

    public static DevicePatientDTO toDevicePatientDTO(DevicePatient devicePatient) {
        DevicePatientDTO dto = new DevicePatientDTO();
        dto.setDevice(devicePatient.getDevice());
        dto.setAssignmentDate(devicePatient.getAssignmentDate());
        dto.setAssignmentEndDate(devicePatient.getAssignmentEndDate());
        return dto;
    }
}
