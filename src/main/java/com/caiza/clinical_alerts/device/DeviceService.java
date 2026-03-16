package com.caiza.clinical_alerts.device;

import com.caiza.clinical_alerts.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired
    public DeviceRepository deviceRepository;

    public DeviceDTO createDevice(Device device){
        Device deviceNew = deviceRepository.findBySerialNumber(device.getSerialNumber());
        if(deviceNew != null){
            throw new BusinessException("Device with the same serial number already exists");
        }
        deviceRepository.save(deviceNew);
        DeviceDTO response = DeviceMapper.toDTO(device);
        return response;
    }
    public Device getDeviceById(Long id){
        Device device = deviceRepository.getById(id);
            if(device == null){
                throw new BusinessException("Device not found");
            }
        return device;
    }

    public void deleteDeviceById(Long id){
        Device device = deviceRepository.getById(id);
        if(device == null){
            throw new BusinessException("Device not found");
        }
        deviceRepository.delete(device);
    }

    public DeviceDTO updateDevice(Long id, DeviceDTO deviceDTO){
        Device deviceUpdate = getDeviceById(id);
        Device device = DeviceMapper.toEntity(deviceDTO);
        deviceUpdate.setModel(device.getModel());
        deviceUpdate.setManufacture(device.getManufacture());
        deviceUpdate.setIsActive(device.getIsActive());
        deviceUpdate.setDeviceType(device.getDeviceType());
        deviceRepository.save(deviceUpdate);
        DeviceDTO response = DeviceMapper.toDTO(deviceUpdate);
        return response;
    }

    public Page<Device> getAllDevices(Pageable pageable){
        Page<Device> device = deviceRepository.findAll(pageable);
        if(device.isEmpty()){
            throw new BusinessException("No devices found");
        }
        return device;
    }
}
