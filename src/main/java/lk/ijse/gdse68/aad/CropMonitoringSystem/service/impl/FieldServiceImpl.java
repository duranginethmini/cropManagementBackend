package lk.ijse.gdse68.aad.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.FieldResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.FieldDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.FieldDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.FieldEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.DataPersistentException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.FieldService;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldDAO fieldDAO;

    @Autowired
    private Mapping mapping;
    @Override
    public void addFields(FieldDTO fieldDTO) {
      fieldDTO.setFieldCode(AppUtil.createFieldId());
        FieldEntity save = fieldDAO.save(mapping.convertToEntity(fieldDTO));
        if (save == null && save.getFieldCode()==null){
            throw new DataPersistentException("Couldn't save Field details!");
        }
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return null;
    }

    @Override
    public FieldResponse selectFieldById(String id) {
        return null;
    }

    @Override
    public void updateField(String id, FieldDTO fieldDTO) {

    }

    @Override
    public void deleteField(String id) {

    }
}
