package lk.ijse.gdse68.aad.CropMonitoringSystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CropMonitoringSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CropMonitoringSystemApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper (){
		return new ModelMapper();
	}

}
