package lk.ijse.gdse68.aad.CropMonitoringSystem.dto;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.FieldResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO, FieldResponse {
    private String fieldCode;
    private String fieldName;
    private Double extentSize;
    private String fieldLocation;
    private String image1;
    private String image2;
    private String equipmentCode;
}
