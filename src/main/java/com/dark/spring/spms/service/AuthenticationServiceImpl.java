package com.dark.spring.spms.service;

import com.dark.spring.spms.data.UserData;
import com.dark.spring.spms.dto.AccessDTO;
import com.dark.spring.spms.dto.LoginUserDTO;
import com.dark.spring.spms.entity.User;
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
    public LoginUserDTO register(UserData userData) {
        User user = userService.register(userData.toModel());
        System.out.println(user.toString());
        return UserData.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build().toLoginDTO();
    }

    @Override
    public AccessDTO authenticate(UserData userData) {
        UserData user;
        String jwtToken = "";
        String exp = "";
        System.out.println(userData.toString());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userData.getEmail(),
                        userData.getPassword()
                )
        );
        try {
            user = userService.getUserByEmail(userData.getEmail());
            jwtToken = jwtService.generateToken(user);
            exp = jwtService.getExpirationTime() + "ms";

        } catch (UsernameNotFoundException e) {
            System.out.println("User not found " + e);
        }
        System.out.println("TOKEN:::" + jwtToken);
        return AccessDTO.builder()
                .token_type("Bearer")
                .token(jwtToken)
                .expires_in(exp)
                .build();
    }
}
