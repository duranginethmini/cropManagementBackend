package lk.ijse.gdse68.aad.CropMonitoringSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health")
public class HealthController {
    @GetMapping
    public String healthCheck(){
        return  "Crop system is running";
    }
}
