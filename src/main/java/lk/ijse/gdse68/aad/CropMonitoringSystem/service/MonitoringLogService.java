package lk.ijse.gdse68.aad.CropMonitoringSystem.service;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.MonitoringLogResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.MonitoringLogDTO;

import java.util.List;

public interface MonitoringLogService {
    void addMonitoringLog(MonitoringLogDTO monitoringLogDTO);

    List<MonitoringLogDTO> getAllLogs ();

    MonitoringLogResponse getSelectedLogs (String id);

    void updateLogs(String id,MonitoringLogDTO monitoringLogDTO);

    void deleteLogs(String id);
}
