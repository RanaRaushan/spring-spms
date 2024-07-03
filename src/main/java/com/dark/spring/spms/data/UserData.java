package com.dark.spring.spms.data;

import com.dark.spring.spms.dto.LoginUserDTO;
import com.dark.spring.spms.dto.UserDTO;
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

    public LoginUserDTO toLoginDTO(){
        return LoginUserDTO.builder()
                .name(name)
                .email(email)
                .build();
    }

    public UserDTO toUserDTO(){
        return UserDTO.builder()
                .name(name)
                .email(email)
                .build();
    }
}
