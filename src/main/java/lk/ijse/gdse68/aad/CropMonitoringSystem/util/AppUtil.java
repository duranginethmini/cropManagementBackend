package lk.ijse.gdse68.aad.CropMonitoringSystem.util;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKTReader;
import org.springframework.data.geo.Point;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.geo.Point;


import java.io.IOException;
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
    public  static String createStaffId(){
        return "Staff" + UUID.randomUUID().toString();
    }

    public static String createFieldId(){return "Field" + UUID.randomUUID().toString();}
    public static String createLogId(){return "Log" + UUID.randomUUID().toString();}

   public static String toBase64Image(MultipartFile file) {
    try {
        byte[] imageBytes = file.getBytes();
        return Base64.getEncoder().encodeToString(imageBytes);
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}





    public static String toBase64ProfilePic(byte [] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }
}
