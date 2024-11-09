package lk.ijse.gdse68.aad.CropMonitoringSystem.dao;

import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoringLogDAO extends JpaRepository<MonitoringLogEntity,String> {
}
