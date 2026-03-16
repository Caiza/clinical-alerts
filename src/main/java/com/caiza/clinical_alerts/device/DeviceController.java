package com.caiza.clinical_alerts.device;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/save")
    public ResponseEntity<DeviceDTO> createDevice(@RequestBody @Valid DeviceDTO deviceDTO){
        Device device = DeviceMapper.toEntity(deviceDTO);
        DeviceDTO response = deviceService.createDevice(device);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDTO> getDeviceById(@RequestParam  Long id) {
        Device device = deviceService.getDeviceById(id);
        DeviceDTO deviceDTO = DeviceMapper.toDTO(device);
        return ResponseEntity.ok(deviceDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<DeviceDTO>> getAllDevices(@RequestParam(defaultValue = "0") Integer page,
                                                         @RequestParam(defaultValue = "0") Integer size,
                                                         @RequestParam(defaultValue = "id") String sortBy,
                                                         @RequestParam(defaultValue = "asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Device> deviceList = deviceService.getAllDevices(pageable);
        Page<DeviceDTO> response = deviceList.map(DeviceMapper::toDTO);
        return ResponseEntity.ok(response);
    }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteDeviceById(@PathVariable Long id) {
         deviceService.deleteDeviceById(id);
         return ResponseEntity.noContent().build();
     }

     @PutMapping("/{id}")
        public ResponseEntity<DeviceDTO> updateDevice(@PathVariable  Long id, @RequestBody @Valid DeviceDTO deviceDTO) {
        DeviceDTO response = deviceService.updateDevice(id, deviceDTO);
        return ResponseEntity.ok(response);
     }

}
