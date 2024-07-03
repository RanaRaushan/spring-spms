package com.dark.spring.spms.controllers;

import com.dark.spring.spms.dto.LoginUserDTO;
import com.dark.spring.spms.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AccessController {

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping(value = "/login")
    public LoginUserDTO login(LoginUserDTO loginUserDTO) {
        // TODO:: Implement
        System.out.println("login:");
        authenticationService.authenticate();
        return null;
    }

    @PostMapping(value = "/register")
    public LoginUserDTO register(@RequestBody LoginUserDTO loginUserDTO) {
        System.out.println("Inside Register of AccessController " + loginUserDTO.toString());
        return authenticationService.register(loginUserDTO.toData());
    }
}
