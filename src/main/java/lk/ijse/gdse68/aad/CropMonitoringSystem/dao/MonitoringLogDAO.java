package lk.ijse.gdse68.aad.CropMonitoringSystem.dao;

import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringLogDAO extends JpaRepository<MonitoringLogEntity,String> {
}
