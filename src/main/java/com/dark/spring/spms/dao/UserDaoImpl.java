package com.dark.spring.spms.dao;

import com.dark.spring.spms.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{
    @Override
    public Optional<User> getUserById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByEmail(String emailId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> save() {
        return Optional.empty();
    }
}
