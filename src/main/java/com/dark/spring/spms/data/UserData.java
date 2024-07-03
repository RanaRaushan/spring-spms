package com.dark.spring.spms.data;

import com.dark.spring.spms.dto.LoginUserDTO;
import com.dark.spring.spms.entity.Customer;
import com.dark.spring.spms.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Builder
@Data
public class UserData {

    private String email;

    private String name;

    private String password;

    private String vehicleNo;

    public User toModel(){
        return Customer.builder()
                .vehicleNo(vehicleNo)
                .email(email)
                .name(name)
                .password(new BCryptPasswordEncoder().encode(password))
                .build();
    }

    public LoginUserDTO toDTO(){
        return LoginUserDTO.builder()
                .name(name)
                .build();
    }
}
