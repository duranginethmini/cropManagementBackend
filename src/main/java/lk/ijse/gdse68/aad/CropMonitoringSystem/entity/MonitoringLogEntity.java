package lk.ijse.gdse68.aad.CropMonitoringSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "MonitoringLog")
public class MonitoringLogEntity {
    @Id
    private String logCode;
    private Date logDate;
    @Column(columnDefinition = "TEXT")
    private String observationDetails;
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fieldCode")
    private FieldEntity field;

    @ManyToMany(mappedBy = "logEntities")
    private List<CropEntity> cropEntities;

    @ManyToMany(mappedBy = "staffLogEntities")
    private List<StaffEntity> staffEntities;

}
