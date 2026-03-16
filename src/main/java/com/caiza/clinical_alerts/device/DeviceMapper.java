package com.caiza.clinical_alerts.device;


public class DeviceMapper {

     public static Device toEntity(DeviceDTO deviceDto){
        return new Device(
        deviceDto.getId(),
        deviceDto.getSerialNumber(),
        deviceDto.getModel(),
        deviceDto.getManufacture(),
        deviceDto.getIsActive(),
        deviceDto.getDeviceType());
    }
    
    public static DeviceDTO toDTO(Device device){
        return new DeviceDTO(
                device.getId(),
                device.getSerialNumber(),
                device.getModel(),
                device.getManufacture(),
                device.getIsActive(),
                device.getDeviceType());
    }
}
