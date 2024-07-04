package com.dark.spring.spms.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ParkingDTO {

    private int slotId;

    private String slotName;

    private Boolean available;

    private UserDTO occupiedBy;

    private String vehicleNo;

    private String createdAt;

    private String updatedAt;

}
