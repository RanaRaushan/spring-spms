package com.dark.spring.spms.service;

import com.dark.spring.spms.dao.UserDao;
import com.dark.spring.spms.data.UserData;
import com.dark.spring.spms.entity.Customer;
import com.dark.spring.spms.exceptions.UserAlreadyExistException;
import com.dark.spring.spms.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public Customer getUserModelByEmail(String email) {
        return userDao.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserData getUserById(int id) throws UserNotFoundException {
        Customer user = userDao.getUserById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return user.toData();
    }

    @Override
    public UserData getUserByEmail(String emailId) throws UserNotFoundException {
        Customer user = userDao.findByEmail(emailId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return user.toData();
    }

    @Override
    public UserData register(Customer user) {
        if (userDao.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User already exist with email : " + user.getEmail());
        }
        return userDao.save(user).toData();
    }

    @Override
    public List<UserData> getAllUser() {
        return userDao.findAll().stream()
                .map(Customer::toData)
                .toList();
    }

    @Override
    public void updateLastLogin(UserData userData) throws UserNotFoundException {
        Optional<Customer> customer = userDao.findByEmail(userData.getEmail());
        if (customer.isPresent()) {
            customer.get().setLastLogin(LocalDateTime.now());
            userDao.save(customer.get());
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
