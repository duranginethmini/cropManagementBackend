package lk.ijse.gdse68.aad.CropMonitoringSystem.controller;


import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.EquipmentResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.EquipmentDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.EquipmentNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/equipment")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class EquipmentController {
     private final EquipmentService equipmentService;

     @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addEquipment(@RequestBody EquipmentDTO equipmentDTO){
         if (equipmentDTO == null){
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }else {
             try {
                 equipmentService.addEquipments(equipmentDTO);
                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
             }catch (EquipmentNotFoundException e){
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }catch (Exception e){
                 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
             }
         }
     }

     @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
     public List<EquipmentDTO> getAllEquipment(){
         return equipmentService.getAllEquipmentsById();
     }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{equipmentCode}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEquipment(@PathVariable("equipmentCode") String equipmentCode, @RequestBody EquipmentDTO equipmentDTO) {
        try {
            if (equipmentDTO == null && (equipmentCode == null || equipmentDTO.equals(""))) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.updateEquipment(equipmentCode, equipmentDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (EquipmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{equipmentCode}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentCode")String equipmentCode){
         try {
             equipmentService.deleteEquipment(equipmentCode);
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }catch (EquipmentNotFoundException e){
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }catch (Exception e){
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @GetMapping(value = "/{equipmentCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentResponse getEquipmentById(@PathVariable("equipmentCode")String equipmentCode){
         return equipmentService.getSelectedEquipment(equipmentCode);
    }
}
