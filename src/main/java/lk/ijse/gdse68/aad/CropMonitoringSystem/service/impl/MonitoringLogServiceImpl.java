package lk.ijse.gdse68.aad.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.MonitoringLogResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.FieldDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.MonitoringLogDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.MonitoringLogDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.CropEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.FieldEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.MonitoringLogEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.DataPersistentException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.FieldNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.MonitoringLogNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.MonitoringLogService;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MonitoringLogServiceImpl implements MonitoringLogService {

    @Autowired
    private MonitoringLogDAO monitoringLogDAO;

    @Autowired
    private FieldDAO fieldDAO;

    @Autowired
    private Mapping mapping;

    @Override
    public void addMonitoringLog(MonitoringLogDTO monitoringLogDTO) {
        monitoringLogDTO.setLogCode(AppUtil.createLogId());
        MonitoringLogEntity save = monitoringLogDAO.save(mapping.convertToEntity(monitoringLogDTO));
        if (save == null && save.getLogCode()==null){
            throw  new DataPersistentException("Couldn't find the related log codes");
        }

    }

    @Override
    public List<MonitoringLogDTO> getAllLogs() {
        return mapping.convertM_ToDTOList(monitoringLogDAO.findAll());
    }

    @Override
    public MonitoringLogResponse getSelectedLogs(String id) {
        if (monitoringLogDAO.existsById(id)){
            return mapping.convertMLogEntityToDto(monitoringLogDAO.getReferenceById(id));
        }else {
            throw new MonitoringLogNotFoundException("There is no such Logs available");
        }
    }

    @Override
    public void updateLogs(String id, MonitoringLogDTO monitoringLogDTO) {
        Optional<MonitoringLogEntity> byId = monitoringLogDAO.findById(id);
        if (!byId.isPresent()){
            throw new MonitoringLogNotFoundException("Couldn't find the entry!");
        } else {
            MonitoringLogEntity monitoringLogEntity = byId.get();
            // Set other fields
            monitoringLogEntity.setLogDate(monitoringLogDTO.getLogDate());
            monitoringLogEntity.setObservationDetails(monitoringLogDTO.getObservationDetails());
            monitoringLogEntity.setObservedImage(monitoringLogDTO.getObservedImage());

            // Fetch the FieldEntity based on fieldCode
            Optional<FieldEntity> fieldEntityOpt = fieldDAO.findById(monitoringLogDTO.getFieldCode());
            if (!fieldEntityOpt.isPresent()) {
                throw new FieldNotFoundException("Field not found for fieldCode: " + monitoringLogDTO.getFieldCode());
            }
            monitoringLogEntity.setField(fieldEntityOpt.get());

            // Save the updated monitoringLogEntity back to the database
            monitoringLogDAO.save(monitoringLogEntity);
        }
    }

    @Override
    public void deleteLogs(String id) {
        Optional<MonitoringLogEntity> byId = monitoringLogDAO.findById(id);
        if (!byId.isPresent()){
            throw new MonitoringLogNotFoundException("Log by this Id does not exist!");
        }else {
            monitoringLogDAO.deleteById(id);
        }
    }
}
