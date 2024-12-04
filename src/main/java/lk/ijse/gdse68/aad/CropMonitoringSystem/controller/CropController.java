package lk.ijse.gdse68.aad.CropMonitoringSystem.controller;

import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.CropResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.CropDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.DataPersistentException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.CropService;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/crops")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class CropController {

    @Autowired
    private final CropService cropService;
    static Logger logger = LoggerFactory.getLogger(CropController.class);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addCrop(
            @RequestParam("cropCode") String cropCode,
            @RequestParam("commonName") String commonName,
            @RequestParam("scientificName") String scientificName,
            @RequestParam("image")MultipartFile image,
            @RequestParam("category") String category,
            @RequestParam("cropSeason")String cropSeason,
            @RequestParam("fieldCode")String fieldCode
            ){
        try {
            String base64Image = AppUtil.toBase64Image(image);
            CropDTO cropDTO = new CropDTO();
            cropDTO.setCropCode(cropCode);
            cropDTO.setCommonName(commonName);
            cropDTO.setScientificName(scientificName);
            cropDTO.setImage(base64Image);
            cropDTO.setCategory(category);
            cropDTO.setCropSeason(cropSeason);
            cropDTO.setFieldCode(fieldCode);
            cropService.addCrops(cropDTO);
            logger.info("Added crop successfully!!");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistentException e){
            logger.error("There was a error while updating");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops(){
        return cropService.getAllCropsById();
    }

    @PutMapping (value = "/{cropCode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity <Void> updateCrops (@PathVariable ("cropCode") String cropCode ,
                                             @RequestParam("commonName") String commonName,
                                             @RequestParam ("scientificName") String scientificName,
                                             @RequestParam("image") MultipartFile image,
                                             @RequestParam("category") String category,
                                             @RequestParam("cropSeason") String cropSeason,
                                              @RequestParam("fieldCode")String fieldCode,
                                             @RequestParam("logCode")List<String> logCode
    ){
        //return userSERVICE.updateUser(id,userDTO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {

            String base64Image = AppUtil.toBase64Image(image);
            CropDTO cropDTO = new CropDTO();
            cropDTO.setCommonName(commonName);
            cropDTO.setScientificName(scientificName);
            cropDTO.setImage(base64Image);
            cropDTO.setCategory(category);
            cropDTO.setCropSeason(cropSeason);
            cropDTO.setFieldCode(fieldCode);
            cropDTO.setLogCode(logCode);
            logger.info("updated crop successfully!");
            cropService.updateCrops(cropCode,cropDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            logger.error("There was an error occurred during updating");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/{cropCode}")
    public ResponseEntity<Void> deleteCrops (@PathVariable ("cropCode")String cropCode){
        try {
            cropService.deleteCrops(cropCode);
            logger.info("Delete successful");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            logger.error("There is no such crop with cropCode");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{cropCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CropResponse getCropsById(@PathVariable ("cropCode") String cropCode){
        return cropService.getSelectedCrops(cropCode);
    }
}
