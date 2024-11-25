package lk.ijse.gdse68.aad.CropMonitoringSystem.controller;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.FieldResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.FieldDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.DataPersistentException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.FieldNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.FieldService;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/field")
@RequiredArgsConstructor
public class FieldController {

    @Autowired
    private FieldService fieldService;

    private static final Logger logger = LoggerFactory.getLogger(FieldController.class);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<String> saveField(
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldSize") double fieldSize,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("fieldImage1") MultipartFile fieldImage1,
            @RequestParam("fieldImage2") MultipartFile fieldImage2,
            @RequestParam("equipmentCode") String equipmentCode) {


        try {
//            // Validate and parse fieldLocation
//            if (fieldLocation == null || !fieldLocation.matches("\\d+(\\.\\d+)?,\\d+(\\.\\d+)?")) {
//                return new ResponseEntity<>("Invalid fieldLocation format. Expected: 'x,y'", HttpStatus.BAD_REQUEST);

            // Convert images to Base64
            byte[] imageByteCollection1 = fieldImage1.getBytes();
            String base64ProfilePic1 = AppUtil.toBase64ProfilePic(imageByteCollection1);

            byte[] imageByteCollection2 = fieldImage2.getBytes();
            String base64ProfilePic2 = AppUtil.toBase64ProfilePic(imageByteCollection2);

            // Build DTO
            FieldDTO buildFieldDTO = new FieldDTO();
            buildFieldDTO.setFieldName(fieldName);
            buildFieldDTO.setFieldSize(fieldSize);
            buildFieldDTO.setFieldLocation(new Point((int) latitude, (int) longitude));
            buildFieldDTO.setFieldImage1(base64ProfilePic1);
            buildFieldDTO.setFieldImage2(base64ProfilePic2);
            buildFieldDTO.setEquipmentCode(equipmentCode);

            // Save field
            fieldService.addFields(buildFieldDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistentException e) {
            return new ResponseEntity<>("Error saving field data", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.err.println("Error occurred while saving item: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllFieldDetail(){
        return fieldService.getAllFields();
    }

    @PatchMapping (value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateFields (
            @PathVariable ("id") String id,
            @RequestParam ("fieldName") String fieldName,
            @RequestParam ("fieldSize") double fieldSize,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("fieldImage1") MultipartFile fieldImage1,
            @RequestParam("fieldImage2") MultipartFile fieldImage2,
            @RequestParam("equipmentCode") String equipmentCode,
            @RequestParam("staffId")List<String> staffId
    ){
        try {
            byte[] imageByteCollection1 = fieldImage1.getBytes();
            String base64ProfilePic1 = AppUtil.toBase64ProfilePic(imageByteCollection1);

            byte[] imageByteCollection2 = fieldImage2.getBytes();
            String base64ProfilePic2 = AppUtil.toBase64ProfilePic(imageByteCollection2);

            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setFieldSize(fieldSize);
            fieldDTO.setFieldLocation(new Point((int) latitude, (int) longitude));
            fieldDTO.setFieldImage1(base64ProfilePic1);
            fieldDTO.setFieldImage2(base64ProfilePic2);
            fieldDTO.setEquipmentCode(equipmentCode);
            fieldDTO.setStaffId(staffId);
            fieldService.updateField(id,fieldDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldResponse getFieldById(  @PathVariable("id") String id){
      return fieldService.selectFieldById(id);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity <FieldResponse> deleteFields (@PathVariable ("id") String id){
        try {
            fieldService.deleteField(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}


