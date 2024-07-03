package com.dark.spring.spms.service;

import com.dark.spring.spms.dao.UserDao;
import com.dark.spring.spms.data.UserData;
import com.dark.spring.spms.entity.User;
import com.dark.spring.spms.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public UserData getUserByEmail(String emailId)  throws UsernameNotFoundException{
        User user = userDao.findByEmail(emailId).orElseThrow(() -> new UsernameNotFoundException("User1 not found"));
        return UserData.builder()
                .name(user.getName())
                .build();
    }

    @Override
    public User register(User user) {
        if (userDao.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User already exist with email : " + user.getEmail());
        }
        return userDao.save(user);
    }

    @Override
    public List<UserData> getAllUser() {
        return userDao.findAll().stream()
                .map(user -> UserData.builder()
                        .name(user.getName())
                        .build())
                .toList();
    }
}
