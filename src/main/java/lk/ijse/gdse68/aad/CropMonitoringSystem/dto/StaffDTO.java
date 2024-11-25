package lk.ijse.gdse68.aad.CropMonitoringSystem.dto;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.StaffResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements SuperDTO, StaffResponse {
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private String gender;
    private LocalDate joinDate;
    private LocalDate dob;
    private String buildingNo;
    private String lane;
    private String city;
    private String state;
    private String postalCode;
    private String contactNo;
    private String email;
    private String equipmentCode;
    private String vehicleCode;
  //  private String fieldCode;
    private List<String> logCode;
}
