package lk.ijse.gdse68.aad.CropMonitoringSystem.service;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.CropResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.VehicleResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.CropDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void addVehicles(VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehiclesById ();

    VehicleResponse getSelectedVehicles (String id);

    void updateVehicles(String id,VehicleDTO vehicleDTO);

    void deleteVehicle(String id);
}
