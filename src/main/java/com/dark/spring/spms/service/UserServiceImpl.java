package com.dark.spring.spms.service;

import com.dark.spring.spms.dao.UserDao;
import com.dark.spring.spms.data.UserData;
import com.dark.spring.spms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public UserData getUserById(int id) {
        User user = userDao.getUserById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserData.builder()
                .name(user.getName())
                .build();
    }

    @Override
    public UserData getUserByEmail(String emailId) {
        User user = userDao.getUserByEmail(emailId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserData.builder()
                .name(user.getName())
                .build();
    }

    @Override
    public UserData register(int id) {
        User user = userDao.getUserById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserData.builder()
                .name(user.getName())
                .build();
    }
}
