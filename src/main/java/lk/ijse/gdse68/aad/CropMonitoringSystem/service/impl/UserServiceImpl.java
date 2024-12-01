package lk.ijse.gdse68.aad.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.UserErrorResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.UserResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.UserDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.UserDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.UserEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.enums.Role;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.DataPersistentException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.UserNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.UserService;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userServiceDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveUser(UserDTO userServiceDTO) {
        var userEntity = mapping.convertToUserEntity( userServiceDTO);
        var saveUser = userServiceDao.save(userEntity);
        if (saveUser==null){
            throw new DataPersistentException("can not save user");
        }
    }

    @Override
    public void updateUser(String email, UserDTO userServiceDTO) {
        UserEntity existingUser = userServiceDao.findById(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        UserEntity updatedUser = new UserEntity();
        updatedUser.setEmail(userServiceDTO.getEmail() != null ? userServiceDTO.getEmail() : existingUser.getEmail());
        updatedUser.setPassword(userServiceDTO.getPassword() != null ? userServiceDTO.getPassword() : existingUser.getPassword());

        if (userServiceDTO.getRole() != null) {
            updatedUser.setRole(Role.valueOf(String.valueOf(userServiceDTO.getRole())));
        } else {
            updatedUser.setRole(existingUser.getRole());
        }

        userServiceDao.delete(existingUser);

        userServiceDao.save(updatedUser);

    }

    @Override
    public void deleteUser(String email) {
        Optional<UserEntity> findId = userServiceDao.findById(email);
        if (!findId.isPresent()){
            throw new UserNotFoundException("User not Found");
        }else {
            userServiceDao.deleteById(email);
        }
    }

    @Override
    public UserResponse getSelectedUser(String email) {
        if (userServiceDao.existsById(email)) {
            UserEntity userEntityByEmail = userServiceDao.getReferenceById(email);
            return mapping.convertUserEntityToDTO(userEntityByEmail);
        } else {
            return new UserErrorResponse(0, "User not Found");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> getAllUsers = userServiceDao.findAll();
        return mapping.convertTOUserDTOList(getAllUsers);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return email ->
                userServiceDao.findByEmail(email)
                        .orElseThrow(()-> new UserNotFoundException("User Not found"));
    }
}
