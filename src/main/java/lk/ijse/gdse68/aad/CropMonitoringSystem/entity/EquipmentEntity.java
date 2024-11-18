package lk.ijse.gdse68.aad.CropMonitoringSystem.entity;


import jakarta.persistence.*;
import lk.ijse.gdse68.aad.CropMonitoringSystem.enums.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "equipment")
@Entity
public class EquipmentEntity implements SuperEntity{
    @Id
    private String equipmentCode;

    private String equipmentName;
    private String status;
    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    @OneToMany(mappedBy = "equipmentEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    private List<StaffEntity> staff;

    @OneToMany(mappedBy = "equipment",cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    private List<FieldEntity> fieldEntities ;
}
