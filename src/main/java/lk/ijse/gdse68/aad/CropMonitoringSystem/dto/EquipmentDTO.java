package lk.ijse.gdse68.aad.CropMonitoringSystem.dto;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.EquipmentResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.enums.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements SuperDTO, EquipmentResponse {
    private String equipmentCode;
    private String equipmentName;
    private String status;
    private EquipmentType type;
}
