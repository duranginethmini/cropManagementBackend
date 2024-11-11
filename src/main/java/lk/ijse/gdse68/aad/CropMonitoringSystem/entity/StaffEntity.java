package lk.ijse.gdse68.aad.CropMonitoringSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "staff")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffEntity implements SuperEntity{
    @Id
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private String gender;
    private LocalDate joinDate;
    private LocalDate dob;
    private String buildingNo;
    private String lane;
    private String city;
    private String state;
    private String postalCode;
    private String contactNo;
    private String email;

    @ManyToMany
    @JoinTable(name = "staff_log",
            joinColumns = @JoinColumn(name = "staffId"),inverseJoinColumns = @JoinColumn(name = "logCode"))
    private List<MonitoringLogEntity> staffLogEntities;

    @ManyToMany(mappedBy = "assignedStaff")
    private List<FieldEntity> fieldEntities;

    @ManyToOne
    @JoinColumn(name ="equipmentCode")
    private EquipmentEntity staff;

    @ManyToOne
    @JoinColumn(name = "vehicleCode")
    private VehicleEntity vehicleEntity;

}
