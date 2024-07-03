package com.dark.spring.spms.service;

import com.dark.spring.spms.data.UserData;
import com.dark.spring.spms.dto.LoginUserDTO;
import com.dark.spring.spms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserService userService;

    @Override
    public LoginUserDTO register(UserData userData) {
        User user = userService.register(userData.toModel());

        return UserData.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build().toDTO();
    }

    @Override
    public LoginUserDTO authenticate() {
        return null;
    }
}
