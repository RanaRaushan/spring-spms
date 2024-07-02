package com.dark.spring.spms.controllers;

import com.dark.spring.spms.dto.UserDTO;
import com.dark.spring.spms.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AccessController {

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping(value = "/login")
    public UserDTO login(@PathVariable int userId) {
        // TODO::
        System.out.println("login:"+ userId);
        authenticationService.authenticate();
        return null;
    }

    @GetMapping(value = "/register")
    public UserDTO register(@PathVariable int userId) {
        // TODO::
        System.out.println("register:"+ userId);
        authenticationService.register();
        return null;
    }
}
