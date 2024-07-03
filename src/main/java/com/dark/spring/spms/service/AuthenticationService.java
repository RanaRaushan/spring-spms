package com.dark.spring.spms.service;


import com.dark.spring.spms.data.UserData;
import com.dark.spring.spms.dto.AccessDTO;
import com.dark.spring.spms.dto.LoginUserDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    LoginUserDTO register(UserData userData);

    AccessDTO authenticate(UserData userData);
}
