package lk.ijse.gdse68.aad.CropMonitoringSystem.dto;

import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.UserEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements SuperDTO {

    private String email;
    private String password;
    private Role role;
}
