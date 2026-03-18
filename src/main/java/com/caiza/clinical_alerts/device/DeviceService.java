package com.caiza.clinical_alerts.device;

import com.caiza.clinical_alerts.exception.BusinessException;
import com.caiza.clinical_alerts.exception.ResourceNotFoundException;
import com.caiza.clinical_alerts.patient.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceService {

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
    public Optional<DeviceDTO> getDeviceById(Long id){
        Device device = deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DevicePatient not found with id: " + id));
        return Optional.of(DeviceMapper.toDTO(device));
    }

    public void deleteDeviceById(Long id){
        Device device = deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DevicePatient not found with id: " + id));
        deviceRepository.delete(device);
    }

    public DeviceDTO updateDevice(Long id, DeviceDTO deviceDTO){
        Device deviceUpdate = deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
        deviceUpdate.setModel(deviceDTO.getModel());
        deviceUpdate.setManufacture(deviceDTO.getManufacture());
        deviceUpdate.setIsActive(deviceDTO.getIsActive());
        deviceUpdate.setDeviceType(deviceDTO.getDeviceType());
        Device device = deviceRepository.save(deviceUpdate);
        return DeviceMapper.toDTO(device);
    }

    public Page<DeviceDTO> getAllDevices(Pageable pageable){
        Page<Device> device = deviceRepository.findAll(pageable);
        if(device.isEmpty()){
            throw new ResourceNotFoundException("No devices found");
        }
        return device.map(DeviceMapper::toDTO);
    }
}
