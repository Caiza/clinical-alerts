package com.caiza.clinical_alerts.deviceAssignmentPatient;

import com.caiza.clinical_alerts.exception.BusinessException;
import com.caiza.clinical_alerts.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DevicePatientService {

        private DevicePatientRepository devicePatientRepository;

        public DevicePatientDTO createDeviceToPatient(DevicePatientDTO devicePatientDTO) {
            DevicePatient devicePatient = DevicePatientMapper.toDevicePatient(devicePatientDTO);
            Optional<DevicePatientDTO> response = getDevicePatientById(devicePatient.getId());
            if(response.isPresent()) {
                throw new BusinessException("DevicePatient is already exist.");
            }
            DevicePatient savedDevicePatient = devicePatientRepository.save(devicePatient);
            return DevicePatientMapper.toDevicePatientDTO(savedDevicePatient);
        }

        public Optional<DevicePatientDTO> getDevicePatientById(Long id) {
            DevicePatient devicePatient = devicePatientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DevicePatient not found with id: " + id));
            return Optional.of(DevicePatientMapper.toDevicePatientDTO(devicePatient));
        }

        public DevicePatientDTO getAllDevicePatient() {
            DevicePatient devicePatient = devicePatientRepository.findAll().stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("No DevicePatient found"));
            return DevicePatientMapper.toDevicePatientDTO(devicePatient);
        }




}
