package lk.ijse.gdse68.aad.CropMonitoringSystem.dto;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.FieldResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO, FieldResponse {

    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private double fieldSize;
    private String fieldImage1;
    private String fieldImage2;
    private String equipmentCode;
    private List<String> staffId;
}
