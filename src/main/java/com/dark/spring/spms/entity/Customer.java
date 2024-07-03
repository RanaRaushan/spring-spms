package com.dark.spring.spms.entity;

import com.dark.spring.spms.data.UserData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@Entity
public class Customer extends User {

    @Column(name = "vehicle_no")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String vehicleNo;


    public UserData toData(){
        return UserData.builder()
                .name(getName())
                .email(getEmail())
                .password(getPassword())
                .vehicleNo(getVehicleNo())
                .createdAt(Objects.nonNull(getCreatedAt()) ? getCreatedAt().toString() : "")
                .updatedAt(Objects.nonNull(getUpdatedAt()) ? getUpdatedAt().toString() : "")
                .lastLogin(Objects.nonNull(getLastLogin()) ? getLastLogin().toString() : "")
                .build();
    }
}