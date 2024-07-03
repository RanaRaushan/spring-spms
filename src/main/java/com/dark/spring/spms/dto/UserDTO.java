package com.dark.spring.spms.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {

    private String name;

    private String email;

    private String vehicleNo;

    private String createdAt;

    private String updatedAt;

    private String lastLogin;

}
