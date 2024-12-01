package lk.ijse.gdse68.aad.CropMonitoringSystem.service;

import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.UserDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.jwtModels.JWTResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.jwtModels.SignIn;

public interface AuthService {
    JWTResponse signIn(SignIn signIn);
    JWTResponse signUp(UserDTO userDTO);
    JWTResponse refreshToken(String accessToken);
}
