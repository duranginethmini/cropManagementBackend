package lk.ijse.gdse68.aad.CropMonitoringSystem.controller;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.VehicleResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.VehicleDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.DataPersistentException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.VehicleNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    @Autowired
    private final VehicleService vehicleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addVehicles(@RequestBody VehicleDTO vehicleDTO) {
        if (vehicleDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try {
                vehicleService.addVehicles(vehicleDTO);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistentException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.getAllVehiclesById();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateVehicle(@PathVariable("id") String id, @RequestBody VehicleDTO vehicleDTO) {
        try {
            if (vehicleDTO == null && (id == null || vehicleDTO.equals(""))) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            vehicleService.updateVehicles(id, vehicleDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (VehicleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("id") String id) {
        try {
            vehicleService.deleteVehicle(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VehicleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleResponse getVehicleById(@PathVariable("id") String id) {
        return vehicleService.getSelectedVehicles(id);
    }
}
