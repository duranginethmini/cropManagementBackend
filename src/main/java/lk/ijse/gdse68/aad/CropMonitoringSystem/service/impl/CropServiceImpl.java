package lk.ijse.gdse68.aad.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.CropResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.CropDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.CropDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.CropEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.DataPersistentException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.CropService;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CropServiceImpl implements CropService {

    @Autowired
    private CropDAO cropDAO;

    @Autowired
    private Mapping mapping;
    @Override
    public void addCrops(CropDTO cropDTO) {
        cropDTO.setCropCode(AppUtil.createOrderId());
        CropEntity add = cropDAO.save(mapping.convertToEntity(cropDTO));
        if (add == null && add.getCropCode()== null){
            throw new DataPersistentException("error occurred during crop persisting!");
        }
    }

    @Override
    public List<CropDTO> getAllCropsByIt() {
        return null;
    }

    @Override
    public CropResponse getSelectedCrops(String id) {
        return null;
    }

    @Override
    public void updateCrops(String id, CropDTO cropDTO) {

    }

    @Override
    public void deleteCrops(String id) {

    }
}
