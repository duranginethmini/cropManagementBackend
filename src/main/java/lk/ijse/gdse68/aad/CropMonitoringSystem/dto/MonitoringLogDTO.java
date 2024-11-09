package lk.ijse.gdse68.aad.CropMonitoringSystem.dto;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.MonitoringLogResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDTO implements SuperDTO, MonitoringLogResponse {
    private String logCode;
    private Date logDate;
    private String observationDetails;
    private String observedImage;
    private String fieldCode;
}
