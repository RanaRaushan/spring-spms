package com.dark.spring.spms.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ParkingUpdateDTO {

    private String slotName;

    private Boolean available;

    private String occupiedBy;

    private String vehicleNo;

    private String createdAt;

    private String updatedAt;

}
