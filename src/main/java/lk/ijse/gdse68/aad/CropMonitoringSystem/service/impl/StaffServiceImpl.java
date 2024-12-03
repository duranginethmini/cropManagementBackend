package lk.ijse.gdse68.aad.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.StaffResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.*;
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
    private FieldDAO fieldDAO;
    @Autowired
    private MonitoringLogDAO monitoringLogDAO;
    @Autowired
    private Mapping mapping;
    @Override
   public void addStaffMembers(StaffDTO staffDTO) {

        // Convert DTO to Entity
        StaffEntity staffEntity = mapping.convertToEntity(staffDTO);

        // Fetch and set the EquipmentEntity if equipmentCode is provided
        if (staffDTO.getEquipmentCode() != null) {
            EquipmentEntity equipmentEntity = equipmentDAO.findById(staffDTO.getEquipmentCode())
                    .orElseThrow(() -> new EquipmentNotFoundException("Equipment with code " + staffDTO.getEquipmentCode() + " not found!"));
            staffEntity.setEquipmentEntity(equipmentEntity);
        }

        // Fetch and set the VehicleEntity if vehicleCode is provided
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
        // Fetch the staff entity by ID
        StaffEntity staffEntity = staffDAO.findById(id)
                .orElseThrow(() -> new StaffNotFoundException("Staff by this ID does not exist!"));

        // Update basic fields from DTO to Entity
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

        // Check if equipmentCode is present and set it
        if (staffDTO.getEquipmentCode() != null) {
            EquipmentEntity equipmentEntity = equipmentDAO.findById(staffDTO.getEquipmentCode())
                    .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found for equipmentCode: " + staffDTO.getEquipmentCode()));
            staffEntity.setEquipmentEntity(equipmentEntity);
        }

        // Check if vehicleCode is present and set it
        if (staffDTO.getVehicleCode() != null) {
            VehicleEntity vehicleEntity = vehicleDAO.findById(staffDTO.getVehicleCode())
                    .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found for vehicleCode: " + staffDTO.getVehicleCode()));
            staffEntity.setVehicleEntity(vehicleEntity);
        }

        // Check if fieldCode is present and set it
        if (staffDTO.getLogCode() != null) {
            // If you want to update multiple fields, you would need to use findAllById
            List<MonitoringLogEntity> logEntities =monitoringLogDAO .findAllById(staffDTO.getLogCode());
            staffEntity.setStaffLogEntities(logEntities);
        }

        // Save the updated staff entity
        staffDAO.save(staffEntity);
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
