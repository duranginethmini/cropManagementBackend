package lk.ijse.gdse68.aad.CropMonitoringSystem.service;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.EquipmentResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.VehicleResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.EquipmentDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.VehicleDTO;

import java.util.List;

public interface EquipmentService {
    void addEquipments(EquipmentDTO equipmentDTO);

    List<EquipmentDTO> getAllEquipmentsById ();

    EquipmentResponse getSelectedEquipment (String id);

    void updateEquipment(String id,EquipmentDTO equipmentDTO);

    void deleteEquipment(String id);
}
