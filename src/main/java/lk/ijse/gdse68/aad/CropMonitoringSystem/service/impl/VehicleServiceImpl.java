package lk.ijse.gdse68.aad.CropMonitoringSystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.VehicleResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dao.VehicleDAO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.VehicleDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.VehicleEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.DataPersistentException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.VehicleNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.VehicleService;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.AppUtil;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDAO vehicleDAO;

    @Autowired
    private Mapping mapping;
    @Override
    public void addVehicles(VehicleDTO vehicleDTO) {
        VehicleEntity save = vehicleDAO.save(mapping.convertToEntity(vehicleDTO));
        if (save == null && save.getVehicleCode()==null){
            throw new DataPersistentException("Error occurred during vehicle data persistent! ");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehiclesById() {
        return mapping.convertV_ToDTOList(vehicleDAO.findAll());
    }

    @Override
    public VehicleResponse getSelectedVehicles(String id) {
       if (vehicleDAO.existsById(id)){
           return mapping.convertVehicleEntityToDTO(vehicleDAO.getReferenceById(id));
       }else {
           throw new VehicleNotFoundException("Vehicle by this id does not exist!");
       }
    }

    @Override
    public void updateVehicles(String id, VehicleDTO vehicleDTO) {
        Optional<VehicleEntity> byId = vehicleDAO.findById(id);
        if (!byId.isPresent()){
            throw new VehicleNotFoundException("Vehicle is nowhere to be found!");
        }else {
            byId.get().setLicensePlate(vehicleDTO.getLicensePlate());
            byId.get().setCategory(vehicleDTO.getCategory());
            byId.get().setFuelType(vehicleDTO.getFuelType());
            byId.get().setStatus(vehicleDTO.getStatus());
            byId.get().setRemarks(vehicleDTO.getRemarks());
        }
    }

    @Override
    public void deleteVehicle(String id) {
        Optional<VehicleEntity> byId = vehicleDAO.findById(id);
        if (!byId.isPresent()){
            throw new VehicleNotFoundException("Vehicle is nowhere to be found!");
        }else {
            vehicleDAO.deleteById(id);
        }
    }
}
