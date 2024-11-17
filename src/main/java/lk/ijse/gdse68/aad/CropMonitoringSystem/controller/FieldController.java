package lk.ijse.gdse68.aad.CropMonitoringSystem.controller;

import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.FieldDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.DataPersistentException;
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






}


