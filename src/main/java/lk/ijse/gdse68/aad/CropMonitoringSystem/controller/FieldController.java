package lk.ijse.gdse68.aad.CropMonitoringSystem.controller;

import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.FieldDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.DataPersistentException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.FieldService;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/field")
@RequiredArgsConstructor
public class FieldController {

    @Autowired
    private FieldService fieldService;

    private static final Logger logger = LoggerFactory.getLogger(FieldController.class);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addField (
            @RequestPart("fieldName") String fieldName,
            @RequestPart("extentSize") double extentSize,
            @RequestPart("fieldLocation") String fieldLocation,
            @RequestPart("image1") MultipartFile image1,
            @RequestPart("image2") MultipartFile image2,
            @RequestPart("equipmentCode")String equipmentCode
    ){
        try {

            String base64Image = AppUtil.toBase64Image(image1);
            String base64Image1 = AppUtil.toBase64Image(image2);
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setExtentSize(extentSize);
            fieldDTO.setFieldLocation(fieldLocation);
            fieldDTO.setImage1(base64Image);
            fieldDTO.setImage2(base64Image1);
            fieldDTO.setEquipmentCode(equipmentCode);
            fieldService.addFields(fieldDTO);
            logger.info("save successfully" + fieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistentException e){
            logger.warn("Received null FieldDTO");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
