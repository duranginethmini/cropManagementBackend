package lk.ijse.gdse68.aad.CropMonitoringSystem.controller;

import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.StaffDTO;
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

@RestController
@RequestMapping("api/v1/fieldStaff")
@RequiredArgsConstructor
public class StaffFieldController {
    @Autowired
    private StaffService staffService;

    static Logger logger = LoggerFactory.getLogger(StaffFieldController.class);
//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> updateStaff(@PathVariable("id") String id, @RequestBody StaffDTO staffDTO) {
//        try {
//            if (staffDTO == null && (id == null || staffDTO.equals(""))) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//            staffService.updateStaff(id, staffDTO);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//        } catch (StaffNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }h
//    }
//}
//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> updateStaff(@PathVariable("id") String id,  @RequestBody StaffDTO staffDTO) {
//        try {
//            // Validate inputs
//            if (staffDTO == null || id == null || id.isEmpty()) {
//                return ResponseEntity.badRequest().body("Invalid input: staffDTO or ID is null/empty");
//            }
//
//            // Call the service to update staff
//            staffService.updateStaff(id, staffDTO);
//            return ResponseEntity.noContent().build(); // or ResponseEntity.ok("Staff updated successfully.");
//        } catch (StaffNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (Exception e) {
//            logger.error("Error updating staff with ID {}: {}", id, e.getMessage(), e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
//        }
//    }
}
