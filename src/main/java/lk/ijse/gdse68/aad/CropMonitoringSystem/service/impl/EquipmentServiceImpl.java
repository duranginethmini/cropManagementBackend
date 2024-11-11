package lk.ijse.gdse68.aad.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.EquipmentResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.EquipmentDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.EquipmentDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.EquipmentEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.VehicleEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.DataPersistentException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.EquipmentNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.VehicleNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.EquipmentService;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDAO equipmentDAO;

    @Autowired
    private Mapping mapping;
    @Override
    public void addEquipments(EquipmentDTO equipmentDTO) {
        equipmentDTO.setEquipmentCode(AppUtil.createEquipmentId());
        EquipmentEntity save = equipmentDAO.save(mapping.convertToEntity(equipmentDTO));
        if (save == null && save.getEquipmentCode()==null){
            throw  new DataPersistentException("Couldn't found the equipment");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipmentsById() {
        return mapping.convertE_ToDTOList(equipmentDAO.findAll());
    }

    @Override
    public EquipmentResponse getSelectedEquipment(String id) {
        if (equipmentDAO.existsById(id)){
            return mapping.convertToDTO(equipmentDAO.getReferenceById(id));
        }else {
            throw new EquipmentNotFoundException("Equipment by this id does not exist!");
        }
    }

    @Override
    public void updateEquipment(String id, EquipmentDTO equipmentDTO) {
        Optional<EquipmentEntity> byId = equipmentDAO.findById(id);
        if (!byId.isPresent()){
            throw new EquipmentNotFoundException("Couldn't find the Equipment!");
        }else {
            byId.get().setEquipmentName(equipmentDTO.getEquipmentName());
            byId.get().setStatus(equipmentDTO.getStatus());
            byId.get().setType(equipmentDTO.getType());

        }
    }

    @Override
    public void deleteEquipment(String id) {
        Optional<EquipmentEntity> byId = equipmentDAO.findById(id);
        if (!byId.isPresent()){
            throw new DataPersistentException("Couldn't find the equipment!");
        }else {
            equipmentDAO.deleteById(id);
        }
    }
}
