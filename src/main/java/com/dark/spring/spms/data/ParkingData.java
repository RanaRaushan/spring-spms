package com.dark.spring.spms.data;

import com.dark.spring.spms.dto.ParkingDTO;
import com.dark.spring.spms.entity.ParkingSlot;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Builder
@Data
public class ParkingData {

    private int slotId;

    private String slotName;

    private Boolean available;

    private UserData occupiedBy;

    private String vehicleNo;

    private String createdAt;

    private String updatedAt;

    public ParkingSlot toModel(){
        return ParkingSlot.builder()
                .slotName(slotName)
                .available(available)
//                .occupiedBy()
                .build();
    }

    public ParkingDTO toDTO(){
        return ParkingDTO.builder()
                .slotId(slotId)
                .slotName(slotName)
                .available(available)
                .occupiedBy(Objects.nonNull(occupiedBy) ? occupiedBy.toUserDTO() : null)
                .vehicleNo(vehicleNo)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
