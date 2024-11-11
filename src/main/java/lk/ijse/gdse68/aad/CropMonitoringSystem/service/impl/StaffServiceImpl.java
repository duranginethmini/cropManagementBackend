package lk.ijse.gdse68.aad.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.StaffResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.StaffDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.StaffDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.StaffEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.VehicleEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.DataPersistentException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.StaffService;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void addStaffMembers(StaffDTO staffDTO) {
        staffDTO.setStaffId(AppUtil.createStaffId());
        StaffEntity save = staffDAO.save(mapping.convertToEntity(staffDTO));
        if (save == null && save.getStaffId()==null){
            throw new DataPersistentException("Error occurred while staff persistent! ");
        }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return null;
    }

    @Override
    public StaffResponse selectStaffById(String id) {
        return null;
    }

    @Override
    public void updateStaff(String id, StaffDTO staffDTO) {

    }

    @Override
    public void deleteStaff(String id) {

    }
}
