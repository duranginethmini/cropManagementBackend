package lk.ijse.gdse68.aad.CropMonitoringSystem.controller;


import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.EquipmentResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.EquipmentDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.EquipmentNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/equipment")
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
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEquipment(@PathVariable("id") String id, @RequestBody EquipmentDTO equipmentDTO) {
        try {
            if (equipmentDTO == null && (id == null || equipmentDTO.equals(""))) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.updateEquipment(id, equipmentDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (EquipmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("id")String id){
         try {
             equipmentService.deleteEquipment(id);
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }catch (EquipmentNotFoundException e){
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }catch (Exception e){
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentResponse getEquipmentById(@PathVariable("id")String id){
         return equipmentService.getSelectedEquipment(id);
    }
}
