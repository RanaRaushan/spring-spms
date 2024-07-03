package com.dark.spring.spms.service;

import com.dark.spring.spms.data.UserData;
import com.dark.spring.spms.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserData getUserById(int id);

    UserData getUserByEmail(String emailId);

    User register(User user);
}
