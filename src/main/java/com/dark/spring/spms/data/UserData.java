package com.dark.spring.spms.data;

import com.dark.spring.spms.dto.LoginUserDTO;
import com.dark.spring.spms.dto.UserDTO;
import com.dark.spring.spms.entity.Customer;
import com.dark.spring.spms.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Builder
@Data
public class UserData {

    private String email;

    private String firstName;

    private String lastName;

    private String fullName;

    private String password;

    private String vehicleNo;

    private String createdAt;

    private String updatedAt;

    private String lastLogin;

    public Customer toModel(){
        return Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .name(firstName + " " + lastName)
                .email(email)
                .password(new BCryptPasswordEncoder().encode(password))
                .lastLogin(LocalDateTime.parse(lastLogin))
                .build();
    }

    public LoginUserDTO toLoginDTO(){
        return LoginUserDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
    }

    public UserDTO toUserDTO(){
        return UserDTO.builder()
                .vehicleNo(vehicleNo)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .lastLogin(lastLogin)
                .build();
    }
}
