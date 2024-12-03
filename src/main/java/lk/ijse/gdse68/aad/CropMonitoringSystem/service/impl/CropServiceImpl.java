package lk.ijse.gdse68.aad.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.CropResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.CropDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.FieldDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.MonitoringLogDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.CropDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.CropEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.EquipmentEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.FieldEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.MonitoringLogEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.*;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.CropService;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {

    @Autowired
    private CropDAO cropDAO;

    @Autowired
    private FieldDAO fieldDAO;

    @Autowired
    private MonitoringLogDAO monitoringLogDAO;

    @Autowired
    private Mapping mapping;
    @Override
    public void addCrops(CropDTO cropDTO) {
        CropEntity add = cropDAO.save(mapping.convertToEntity(cropDTO));
        if (add == null && add.getCropCode()== null){
            throw new DataPersistentException("error occurred during crop persisting!");
        }
    }

    @Override
    public List<CropDTO> getAllCropsById() {
        return mapping.convertToDTO(cropDAO.findAll());
    }

    @Override
    public CropResponse getSelectedCrops(String id) {
        if (cropDAO.existsById(id)){
            return mapping.convertToDTO(cropDAO.getReferenceById(id));
        }else {
            throw new CropNotFoundException("There's no such crop");
        }
    }

    @Override
    public void updateCrops(String id, CropDTO cropDTO) {
        Optional<CropEntity> byId = cropDAO.findById(id);
        if (!byId.isPresent()) {
            throw new CropNotFoundException("Couldn't find the crop!");
        } else {
            CropEntity cropEntity = byId.get();
            // Set other fields
            cropEntity.setCommonName(cropDTO.getCommonName());
            cropEntity.setScientificName(cropDTO.getScientificName());
            cropEntity.setImage(cropDTO.getImage());
            cropEntity.setCategory(cropDTO.getCategory());
            cropEntity.setCropSeason(cropDTO.getCropSeason());

            // Fetch the FieldEntity based on fieldCode
            Optional<FieldEntity> fieldEntityOpt = fieldDAO.findById(cropDTO.getFieldCode());
            if (!fieldEntityOpt.isPresent()) {
                throw new FieldNotFoundException("Field not found for fieldCode: " + cropDTO.getFieldCode());
            }
            cropEntity.setField(fieldEntityOpt.get());

            if (cropDTO.getLogCode() != null) {
                // If you want to update multiple fields, you would need to use findAllById
                List<MonitoringLogEntity> logEntities = monitoringLogDAO.findAllById(cropDTO.getLogCode());
                cropEntity.setLogEntities(logEntities);
            }

                // Save the updated cropEntity back to the database
                cropDAO.save(cropEntity);
            }
        }



    @Override
    public void deleteCrops(String id) {
        Optional<CropEntity> byId = cropDAO.findById(id);
        if (!byId.isPresent()){
            throw new CropNotFoundException("Crop by this Id does not exist!");
        }else {
            cropDAO.deleteById(id);
        }
    }
}
