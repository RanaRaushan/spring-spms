package com.dark.spring.spms.dto;

import com.dark.spring.spms.data.UserData;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@JsonFilter("ignorePasswordFilter")
public class LoginUserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public UserData toData(){
        return UserData.builder()
                .firstName(firstName)
                .lastName(lastName)
                .fullName(firstName + " " + lastName)
                .email(email)
                .password(password)
                .lastLogin(LocalDateTime.now().toString())
                .build();
    }
}
