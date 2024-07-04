package com.dark.spring.spms.entity;

import com.dark.spring.spms.data.ParkingData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "parking_slot")
public class ParkingSlot extends BaseModel{

    @Column(name = "name", unique = true, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String slotName;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "vehicle_no")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String vehicleNo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "occupied_by_id")
    private Customer occupiedBy;

    @PrePersist
    protected void onUpdate() {
        this.available = null == occupiedBy;
    }

    public ParkingData toData(){
        return ParkingData.builder()
                .slotId(getId())
                .slotName(slotName)
                .available(available)
                .occupiedBy(Objects.nonNull(occupiedBy) ? occupiedBy.toData() : null)
                .vehicleNo(vehicleNo)
                .createdAt(Objects.nonNull(getCreatedAt()) ? getCreatedAt().toString() : "")
                .updatedAt(Objects.nonNull(getUpdatedAt()) ? getUpdatedAt().toString() : "")
                .build();
    }
}

