package com.dark.spring.spms.controllers;


import com.dark.spring.spms.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

//    @Autowired
//    private UserService userService;

//    Run at http://localhost:8080/users/1
    @GetMapping(value = "/{userId}")
    public UserDTO getUser(@PathVariable int userId) {
        // TODO::
        System.out.println("Fetching user for Id:"+ userId);
        return UserDTO.builder().name("Rana Rana").build();
    }

}
