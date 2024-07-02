package com.dark.spring.spms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "parking_slot")
public class ParkingSlot extends BaseModel{

    @Column(name = "name")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String slotName;

    @Column(name = "available")
    private Boolean available;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "occupied_by_id")
    private User occupiedBy;

}