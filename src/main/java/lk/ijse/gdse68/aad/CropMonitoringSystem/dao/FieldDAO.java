package lk.ijse.gdse68.aad.CropMonitoringSystem.dao;

import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldDAO extends JpaRepository<FieldEntity,String> {
}
