package com.dark.spring.spms.service;

import com.dark.spring.spms.data.UserData;
import com.dark.spring.spms.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserData getUserById(int id);

    UserData getUserByEmail(String emailId);

    UserData register(Customer user);

    List<UserData> getAllUser();

    void updateLastLogin(UserData userData);
}
