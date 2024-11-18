package lk.ijse.gdse68.aad.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.FieldResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.EquipmentDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.FieldDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.EquipmentDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.FieldDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.CropEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.EquipmentEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.FieldEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.DataPersistentException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.FieldNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.FieldService;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.Mapping;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldDAO fieldDAO;

    @Autowired
    private EquipmentDAO equipmentDAO;

    @Autowired
    private Mapping mapping;

@Override
public String addFields(FieldDTO fieldDTO) {
    fieldDTO.setFieldCode(AppUtil.createFieldId());
    FieldEntity save = fieldDAO.save(mapping.convertToEntity(fieldDTO));

    // Check if saving failed or if the field code is null
    if (save == null || save.getFieldCode() == null) {
        return "Couldn't save Field details!";
    }

    // Return a success message if saving was successful
    return "Field added successfully with code: " + save.getFieldCode();


}


    @Override
    public List<FieldDTO> getAllFields() {
        return mapping.convertF_EntityListToDTOList(fieldDAO.findAll());
    }

    @Override
    public FieldResponse selectFieldById(String id) {
        if (fieldDAO.existsById(id)){
            return mapping.convertFieldEntityToDTO(fieldDAO.getReferenceById(id));
        }else {
            throw new CropNotFoundException("There's no such crop");
        }
    }

    @Override
    public void updateField(String id, FieldDTO fieldDTO) {
        Optional<FieldEntity> byId = fieldDAO.findById(id);
        if (!byId.isPresent()){
            throw new FieldNotFoundException("Couldn't find the field!");
        } else {
            FieldEntity field = byId.get();
            // Set other fields
            field.setFieldName(fieldDTO.getFieldName());
            field.setFieldSize(fieldDTO.getFieldSize());
            field.setFieldLocation(fieldDTO.getFieldLocation());
            field.setImage1(fieldDTO.getFieldImage1());
            field.setImage2(fieldDTO.getFieldImage2());


            // Fetch the FieldEntity based on fieldCode
            Optional<EquipmentEntity>equipmentEntity = equipmentDAO.findById(fieldDTO.getEquipmentCode());
            if (!equipmentEntity.isPresent()) {
                throw new FieldNotFoundException("Field not found for EquipmentCode: " + fieldDTO.getEquipmentCode());
            }
            field.setEquipment(equipmentEntity.get());

            // Save the updated cropEntity back to the database
            fieldDAO.save(field);
        }
    }

    @Override
    public void deleteField(String id) {
        Optional<FieldEntity> byId = fieldDAO.findById(id);
        if (!byId.isPresent()){
            throw new FieldNotFoundException("Field by this Id does not exist!");
        }else {
            fieldDAO.deleteById(id);
        }
    }
}
