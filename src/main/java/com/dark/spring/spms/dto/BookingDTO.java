package com.dark.spring.spms.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookingDTO {

    private Object slot;

    private String vehicleNo;

    private String user;

}
