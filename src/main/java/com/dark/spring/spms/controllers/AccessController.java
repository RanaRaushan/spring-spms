package com.dark.spring.spms.controllers;

import com.dark.spring.spms.dto.AccessDTO;
import com.dark.spring.spms.dto.LoginUserDTO;
import com.dark.spring.spms.service.AuthenticationService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
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
    public AccessDTO login(@RequestBody LoginUserDTO loginUserDTO) {
        return authenticationService.authenticate(loginUserDTO);
    }

    @PostMapping(value = "/register")
    public MappingJacksonValue register(@RequestBody LoginUserDTO loginUserDTO) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(authenticationService.register(loginUserDTO.toData()));
        FilterProvider ignorePasswordFilter = new SimpleFilterProvider()
                .addFilter("ignorePasswordFilter", SimpleBeanPropertyFilter.serializeAllExcept("password"));
        mappingJacksonValue.setFilters(ignorePasswordFilter);
        return mappingJacksonValue;
    }
}
