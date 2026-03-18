package com.caiza.clinical_alerts.device;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private DeviceService deviceService;

    @PostMapping("/save")
    @Operation(summary = "Create a new device", description = "Creates a new device with the provided details.")
    public ResponseEntity<DeviceDTO> createDevice(@RequestBody @Valid DeviceDTO deviceDTO){
        Device device = DeviceMapper.toEntity(deviceDTO);
        DeviceDTO response = deviceService.createDevice(device);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get device by ID", description = "Retrieves a device by their unique ID.")
    public ResponseEntity<Optional<DeviceDTO>> getDeviceById(@RequestParam  Long id) {
        Optional<DeviceDTO> deviceDTO = deviceService.getDeviceById(id);
        return ResponseEntity.ok(deviceDTO);
    }

    @GetMapping("/list")
    @Operation(summary = "Get all devices", description = "Retrieves a list of all devices.")
    public ResponseEntity<Page<DeviceDTO>> getAllDevices(@RequestParam(defaultValue = "0") Integer page,
                                                         @RequestParam(defaultValue = "0") Integer size,
                                                         @RequestParam(defaultValue = "id") String sortBy,
                                                         @RequestParam(defaultValue = "asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DeviceDTO> deviceList = deviceService.getAllDevices(pageable);
        return ResponseEntity.ok(deviceList);
    }

     @DeleteMapping("/{id}")
     @Operation(summary = "Delete device by ID", description = "Deletes a device by their unique ID.")
     public ResponseEntity<Void> deleteDeviceById(@PathVariable Long id) {
         deviceService.deleteDeviceById(id);
         return ResponseEntity.noContent().build();
     }

     @PutMapping("/{id}")
     @Operation(summary = "Update device by ID", description = "Updates a device by their unique ID with the provided details.")
        public ResponseEntity<DeviceDTO> updateDevice(@PathVariable  Long id, @RequestBody @Valid DeviceDTO deviceDTO) {
        DeviceDTO response = deviceService.updateDevice(id, deviceDTO);
        return ResponseEntity.ok(response);
     }

}
