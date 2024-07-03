package com.dark.spring.spms.dao;

import com.dark.spring.spms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer>  {

    Optional<User> getUserById(int id);

    Optional<User> getUserByEmail(String emailId);

}
