package lk.ijse.gdse68.aad.CropMonitoringSystem.service;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.CropResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.CropDTO;

import java.util.List;

public interface CropService {
    void addCrops(CropDTO cropDTO);

    List<CropDTO> getAllCropsByIt ();

    CropResponse getSelectedCrops (String id);

    void updateCrops(String id,CropDTO cropDTO);

    void deleteCrops(String id);
}
