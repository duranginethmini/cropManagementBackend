package lk.ijse.gdse68.aad.CropMonitoringSystem.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Random;
import java.util.UUID;

public class AppUtil {
    public static String createOrderId(){
        return "O" + String.format("%05d", new Random().nextInt(10000));
    }

    public  static String createVehicleId(){
        return "Vehicle" + UUID.randomUUID().toString();
    }

    public  static String createEquipmentId(){
        return "Equip" + UUID.randomUUID().toString();
    }
    public static String toBase64Image(MultipartFile profilepic){
        String profilepicBase64=null;
        try{
            byte [] imagebyteCollection= profilepic.getBytes();
            profilepicBase64= Base64.getEncoder().encodeToString(imagebyteCollection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return profilepicBase64;
    }
}
