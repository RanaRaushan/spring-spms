package com.dark.spring.spms.controllers;


import com.dark.spring.spms.data.UserData;
import com.dark.spring.spms.dto.ParkingDTO;
import com.dark.spring.spms.dto.UserDTO;
import com.dark.spring.spms.service.ParkingService;
import com.dark.spring.spms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public List<UserDTO> getAllUsers() {
        return userService.getAllUser().stream().map(UserData::toUserDTO).toList();
    }


//    Run at http://localhost:8080/users/1
    @GetMapping(value = "/{userId}")
    public EntityModel<UserDTO> getUser(@PathVariable int userId) {
        UserData userData = userService.getUserById(userId);
        EntityModel<UserDTO> entityModel = EntityModel.of(userData.toUserDTO());

        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        entityModel.add(webMvcLinkBuilder.withRel("all-users"));
        return entityModel;
    }

}
