package com.dark.spring.spms.service;

import com.dark.spring.spms.data.UserData;
import com.dark.spring.spms.dto.AccessDTO;
import com.dark.spring.spms.dto.LoginUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public LoginUserDTO register(UserData registerUserData) {
        UserData userData = userService.register(registerUserData.toModel());
        return userData.toLoginDTO();
    }

    @Override
    public AccessDTO authenticate(LoginUserDTO loginUserDTO) {
        UserData user;
        String jwtToken = "";
        String exp = "";
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDTO.getEmail(),
                        loginUserDTO.getPassword()
                )
        );
        try {
            user = userService.getUserByEmail(loginUserDTO.getEmail());
            userService.updateLastLogin(loginUserDTO.toData());
            jwtToken = jwtService.generateToken(user);
            exp = jwtService.getExpirationTime() + "ms";

        } catch (UsernameNotFoundException e) {
            System.out.println("User not found " + e);
        }
        return AccessDTO.builder()
                .token_type("Bearer")
                .token(jwtToken)
                .expires_in(exp)
                .build();
    }
}
