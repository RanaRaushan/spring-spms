package com.dark.spring.spms.dto;

import com.dark.spring.spms.data.UserData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class LoginUserDTO {

    private String name;

    private String email;

    private String password;

    public UserData toData(){
        return UserData.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
