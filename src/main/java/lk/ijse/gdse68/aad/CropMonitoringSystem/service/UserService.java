package lk.ijse.gdse68.aad.CropMonitoringSystem.service;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.UserResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userServiceDTO);
    void updateUser(String email, UserDTO userServiceDTO);
    void deleteUser(String email);
    UserResponse getSelectedUser(String email);
    List<UserDTO> getAllUsers();
    UserDetailsService userDetailsService();
}
