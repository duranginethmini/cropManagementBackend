package lk.ijse.gdse68.aad.CropMonitoringSystem.service;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.FieldResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.StaffResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.FieldDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.StaffDTO;

import java.util.List;

public interface FieldService {
    //void addFields(FieldDTO fieldDTO);
    String addFields(FieldDTO fieldDTO);

    List<FieldDTO> getAllFields ();

    FieldResponse selectFieldById (String id);

    void updateField(String id,FieldDTO fieldDTO);

    void deleteField(String id);
}
