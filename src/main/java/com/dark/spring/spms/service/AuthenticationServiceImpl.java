package com.dark.spring.spms.service;

import com.dark.spring.spms.dto.LoginUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserService userService;

    @Override
    public LoginUserDTO register() {
        return null;
    }

    @Override
    public LoginUserDTO authenticate() {
        return null;
    }
}
