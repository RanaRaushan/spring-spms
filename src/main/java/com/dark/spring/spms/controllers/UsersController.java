package com.dark.spring.spms.controllers;


import com.dark.spring.spms.data.UserData;
import com.dark.spring.spms.dto.UserDTO;
import com.dark.spring.spms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public List<UserDTO> getAllUsers() {
        // TODO::
        System.out.println("Fetching all user:");
        return Collections.singletonList(UserDTO.builder().name("Rana Rana").build());
    }


//    Run at http://localhost:8080/users/1
    @GetMapping(value = "/{userId}")
    public EntityModel<UserDTO> getUser(@PathVariable int userId) {
        // TODO::
        System.out.println("Fetching user for Id:"+ userId);
        UserData userData = userService.getUserById(userId);
        EntityModel<UserDTO> entityModel = EntityModel.of(UserDTO.builder().name(userData.getName()).build());

        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        entityModel.add(webMvcLinkBuilder.withRel("all-users"));
        return entityModel;
    }

}
