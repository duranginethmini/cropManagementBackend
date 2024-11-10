package lk.ijse.gdse68.aad.CropMonitoringSystem.dto;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.VehicleResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class VehicleDTO implements SuperDTO, VehicleResponse {
    private String vehicleCode;
    private String licensePlate;
    private String category;
    private String fuelType;
    private String status;
    private String remarks;
}
