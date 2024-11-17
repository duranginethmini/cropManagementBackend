package lk.ijse.gdse68.aad.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.StaffResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.EquipmentDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.StaffDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.VehicleDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.StaffDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.*;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.*;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.StaffService;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private EquipmentDAO equipmentDAO;
    @Autowired
    private VehicleDAO vehicleDAO;
    @Autowired
    private Mapping mapping;
    @Override
   public void addStaffMembers(StaffDTO staffDTO) {

            // Generate a unique staffId
        staffDTO.setStaffId(AppUtil.createStaffId());

    // Convert DTO to Entity
    StaffEntity staffEntity = mapping.convertToEntity(staffDTO);

    // Fetch the VehicleEntity if vehicleCode is provided
    if (staffDTO.getVehicleCode() != null) {
        VehicleEntity vehicleEntity = vehicleDAO.findById(staffDTO.getVehicleCode())
                .orElseThrow(() -> new DataPersistentException("Vehicle with code " + staffDTO.getVehicleCode() + " not found!"));
        staffEntity.setVehicleEntity(vehicleEntity);
    }

    // Save the StaffEntity
    StaffEntity savedEntity = staffDAO.save(staffEntity);

    // Check if saving was successful
    if (savedEntity == null || savedEntity.getStaffId() == null) {
        throw new DataPersistentException("Error occurred while staff persistent!");
    }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return mapping.convertS_ToDTOList(staffDAO.findAll());
    }

    @Override
    public StaffResponse selectStaffById(String id) {
        if (staffDAO.existsById(id)){
            return mapping.convertStaffEntityToDTO(staffDAO.getReferenceById(id));
        }else {
            throw new StaffNotFoundException("Staff by this ID does not exist!");
        }
    }

    @Override
    public void updateStaff(String id, StaffDTO staffDTO) {
        Optional<StaffEntity> byId = staffDAO.findById(id);
        if (!byId.isPresent()) {
            throw new StaffNotFoundException("Staff by this ID does not exist!");
        } else {
            StaffEntity staffEntity = byId.get();

            // Update other fields
            staffEntity.setFirstName(staffDTO.getFirstName());
            staffEntity.setLastName(staffDTO.getLastName());
            staffEntity.setDesignation(staffDTO.getDesignation());
            staffEntity.setGender(staffDTO.getGender());
            staffEntity.setJoinDate(staffDTO.getJoinDate());
            staffEntity.setDob(staffDTO.getDob());
            staffEntity.setBuildingNo(staffDTO.getBuildingNo());
            staffEntity.setLane(staffDTO.getLane());
            staffEntity.setCity(staffDTO.getCity());
            staffEntity.setState(staffDTO.getState());
            staffEntity.setPostalCode(staffDTO.getPostalCode());
            staffEntity.setContactNo(staffDTO.getContactNo());
            staffEntity.setEmail(staffDTO.getEmail());

            // Fetch and set the EquipmentEntity based on equipmentCode
            Optional<EquipmentEntity> equipment = equipmentDAO.findById(staffDTO.getEquipmentCode());
            if (!equipment.isPresent()) {
                throw new EquipmentNotFoundException("Equipment not found for equipmentCode: " + staffDTO.getEquipmentCode());
            }
            staffEntity.setStaff(equipment.get());

            // Fetch and set the VehicleEntity based on vehicleCode
            Optional<VehicleEntity> vehicle = vehicleDAO.findById(staffDTO.getVehicleCode());
            if (!vehicle.isPresent()) {
                throw new VehicleNotFoundException("Vehicle not found for vehicleCode: " + staffDTO.getVehicleCode());
            }
            staffEntity.setVehicleEntity(vehicle.get());

            // Save the updated staffEntity back to the database
            staffDAO.save(staffEntity);
        }
    }


    @Override
    public void deleteStaff(String id) {
        Optional<StaffEntity> byId = staffDAO.findById(id);
        if (!byId.isPresent()){
            throw new DataPersistentException("Couldn't find the employee!");
        }else {
            staffDAO.deleteById(id);
        }
    }
}
