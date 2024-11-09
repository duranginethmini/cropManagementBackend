package lk.ijse.gdse68.aad.CropMonitoringSystem.dao;

import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropDAO extends JpaRepository<CropEntity,String> {
}
