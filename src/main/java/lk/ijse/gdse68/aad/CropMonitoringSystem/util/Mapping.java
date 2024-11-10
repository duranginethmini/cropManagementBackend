package lk.ijse.gdse68.aad.CropMonitoringSystem.util;


import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.*;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    public ModelMapper modelMapper;

    //crop
    public CropDTO convertToDTO(CropEntity entity) {
        return modelMapper.map(entity,CropDTO.class);
    }
    public CropEntity convertToEntity(CropDTO dto) {
        return modelMapper.map(dto, CropEntity.class);
    }
    public List<CropDTO> convertToDTO(List<CropEntity> crops) {

        return modelMapper.map(crops, new TypeToken<List<CropDTO>>(){}.getType());
    }

    //equipment

    public EquipmentDTO convertToDTO(EquipmentEntity entity) {
        return modelMapper.map(entity,EquipmentDTO.class);
    }
    public EquipmentEntity convertToEntity(EquipmentDTO dto) {
        return modelMapper.map(dto, EquipmentEntity.class);
    }
    public List<EquipmentDTO> convertE_ToDTOList(List<EquipmentEntity> equipment) {
        return modelMapper.map(equipment, new TypeToken<List<EquipmentDTO>>(){}.getType());
    }

    //Field
    public FieldDTO convertFieldEntityToDTO(FieldEntity entity) {
        return modelMapper.map(entity,FieldDTO.class);
    }
    public FieldEntity convertToEntity(FieldDTO dto) {
        return modelMapper.map(dto, FieldEntity.class);
    }
    public List<FieldDTO> convertF_EntityListToDTOList(List<FieldEntity> fields) {
        return modelMapper.map(fields, new TypeToken<List<FieldDTO>>(){}.getType());
    }

    //Monitoring Log
    public MonitoringLogDTO convertMLogEntityToDto(MonitoringLogEntity entity) {
        return modelMapper.map(entity,MonitoringLogDTO.class);
    }
    public MonitoringLogEntity convertToEntity(MonitoringLogDTO dto) {
        return modelMapper.map(dto, MonitoringLogEntity.class);
    }
    public List<MonitoringLogDTO> convertM_ToDTOList(List<MonitoringLogEntity> logs) {
        return modelMapper.map(logs, new TypeToken<List<MonitoringLogDTO>>(){}.getType());
    }

    //Staff
    public StaffDTO convertStaffEntityToDTO(StaffEntity entity) {
        return modelMapper.map(entity,StaffDTO.class);
    }
    public StaffEntity convertToEntity(StaffDTO dto) {
        return modelMapper.map(dto, StaffEntity.class);
    }
    public List<StaffDTO> convertS_ToDTOList(List<StaffEntity> staffs) {
        return modelMapper.map(staffs, new TypeToken<List<StaffDTO>>(){}.getType());
    }

    //Vehicle
    public VehicleDTO convertVehicleEntityToDTO(VehicleEntity entity) {
        return modelMapper.map(entity,VehicleDTO.class);
    }
    public VehicleEntity convertToEntity(VehicleDTO dto) {
        return modelMapper.map(dto, VehicleEntity.class);
    }
    public List<VehicleDTO> convertV_ToDTOList(List<VehicleEntity> vehicles) {
        return modelMapper.map(vehicles, new TypeToken<List<VehicleDTO>>(){}.getType());
    }
}


