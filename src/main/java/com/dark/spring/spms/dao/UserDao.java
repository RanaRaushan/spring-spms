package com.dark.spring.spms.dao;

import com.dark.spring.spms.data.UserData;
import com.dark.spring.spms.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao {

    Optional<User> getUserById(int id);

    Optional<User> getUserByEmail(String emailId);

    Optional<User> save();
}
