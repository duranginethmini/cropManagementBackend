package lk.ijse.gdse68.aad.CropMonitoringSystem.controller;


import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.StaffDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.StaffNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/staff")
@RequiredArgsConstructor
public class StaffController {
    @Autowired
    private final StaffService staffService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addStaff(@RequestBody StaffDTO staffDTO) {
        if (staffDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try {
                System.out.println("Staff saved");
                staffService.addStaffMembers(staffDTO);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch ( StaffNotFoundException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
