package com.dark.spring.spms.dao;

import com.dark.spring.spms.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<Customer, Integer>  {

    Optional<Customer> getUserById(int id);

    Optional<Customer> findByEmail(String emailId);

}
