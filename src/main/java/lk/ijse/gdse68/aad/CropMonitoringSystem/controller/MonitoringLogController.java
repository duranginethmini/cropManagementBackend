package lk.ijse.gdse68.aad.CropMonitoringSystem.controller;


import lk.ijse.gdse68.aad.CropMonitoringSystem.customObj.MonitoringLogResponse;
import lk.ijse.gdse68.aad.CropMonitoringSystem.dto.MonitoringLogDTO;
import lk.ijse.gdse68.aad.CropMonitoringSystem.entity.MonitoringLogEntity;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.CropNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.DataPersistentException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.FieldNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.exception.MonitoringLogNotFoundException;
import lk.ijse.gdse68.aad.CropMonitoringSystem.service.MonitoringLogService;
import lk.ijse.gdse68.aad.CropMonitoringSystem.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/monitoring")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class MonitoringLogController {

    private final MonitoringLogService monitoringLogService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void>saveLogs(
            @RequestParam("logDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, //    @RequestParam("logDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date logDate
            @RequestParam("observationDetail") String observationDetail,
            @RequestParam("observedImage")MultipartFile observedImage,
            @RequestParam("fieldCode")String fieldCode
            ){
        try {
            String base64Image = AppUtil.toBase64Image(observedImage);
            MonitoringLogDTO monitoringLogDTO = new MonitoringLogDTO();
            monitoringLogDTO.setLogDate(date);
            monitoringLogDTO.setObservationDetails(observationDetail);
            monitoringLogDTO.setObservedImage(base64Image);
            monitoringLogDTO.setFieldCode(fieldCode);
            monitoringLogService.addMonitoringLog(monitoringLogDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringLogResponse getLogsByID (@PathVariable ("id") String id){
        return monitoringLogService.getSelectedLogs(id);
    }
    @PatchMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateMonitoringLogs(@PathVariable ("id") String id,
                                                     @RequestParam ("logDate") @DateTimeFormat (pattern = "yyyy-MM-dd")Date date,
                                                     @RequestParam ("observationDetail")String observationDetail,
                                                     @RequestParam("observedImage") MultipartFile observedImage,
                                                     @RequestParam ("fieldCode") String fieldCode){
        try {
            String image = AppUtil.toBase64Image(observedImage);
            MonitoringLogDTO monitoringLogDTO = new MonitoringLogDTO();
            monitoringLogDTO.setLogDate(date);
            monitoringLogDTO.setObservationDetails(observationDetail);
            monitoringLogDTO.setObservedImage(image);
            monitoringLogDTO.setFieldCode(fieldCode);
            monitoringLogService.updateLogs(id,monitoringLogDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (MonitoringLogNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }


        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitoringLogDTO> getAllLogs(){
        return monitoringLogService.getAllLogs();
        }

        @DeleteMapping(value = "/{id}")
   public ResponseEntity <Void> deleteLogs (@PathVariable ("id") String id) {
            try {
                monitoringLogService.deleteLogs(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }catch (MonitoringLogNotFoundException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }



