package lk.ijse.gdse68.aad.CropMonitoringSystem.service;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.StaffResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.VehicleResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.StaffDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.VehicleDTO;

import java.util.List;

public interface StaffService {
    void addStaffMembers(StaffDTO staffDTO);

    List<StaffDTO> getAllStaff ();

    StaffResponse selectStaffById (String id);

    void updateStaff(String id,StaffDTO staffDTO);

    void deleteStaff(String id);
}
