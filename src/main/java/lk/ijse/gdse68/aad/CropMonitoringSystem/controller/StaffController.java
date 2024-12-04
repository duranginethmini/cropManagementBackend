package lk.ijse.gdse68.aad.CropMonitoringSystem.controller;


import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.StaffResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.EquipmentDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.StaffDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.EquipmentNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.StaffNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class StaffController {
    @Autowired
    private final StaffService staffService;
    static Logger logger = LoggerFactory.getLogger(StaffController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addStaff(@RequestBody StaffDTO staffDTO) {
        if (staffDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try {
                System.out.println("Staff saved");
                staffService.addStaffMembers(staffDTO);
                logger.info("Staff saved successfully!");
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (StaffNotFoundException e) {
                logger.error("There was an error popped up during persisting of staff");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getAllStaff (){
        return staffService.getAllStaff();
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateStaff(@PathVariable("id") String id, @RequestBody StaffDTO staffDTO) {
        try {
            if (staffDTO == null && (id == null || staffDTO.equals(""))) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.updateStaff(id, staffDTO);
            logger.info("Update staff successfully!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffResponse getStaffByID(@PathVariable ("id")String id){
        return staffService.selectStaffById(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity <Void> deleteStaff (@PathVariable ("id") String id){
        try {
            staffService.deleteStaff(id);
            logger.info("Deleted staff");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StaffNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
