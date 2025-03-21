package lk.ijse.gdse68.aad.CropMonitoringSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "field")
public class FieldEntity implements SuperEntity{
    @Id
    private String fieldCode;
    private String fieldName;
    @Column
    private double fieldSize;

    @Column
    private Point fieldLocation;

    @Column(columnDefinition = "LONGTEXT")
    private String image1;  // Renamed from "fieldImage1"

    @Column(columnDefinition = "LONGTEXT")
    private String image2;  // Renamed from "fieldImage2"

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<CropEntity> crops;

    @ManyToOne
    @JoinColumn(name = "equipmentCode")
    private EquipmentEntity equipment;

    @ManyToMany
    @JoinTable(
            name = "field_staff_details",
            joinColumns = @JoinColumn(name = "fieldCode"),
            inverseJoinColumns = @JoinColumn(name = "staffId")
    )
    private List<StaffEntity> assignedStaff;

//
    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<MonitoringLogEntity> monitoringLogEntities;
}
