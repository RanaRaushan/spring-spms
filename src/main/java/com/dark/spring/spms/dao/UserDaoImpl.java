//package com.dark.spring.spms.dao;
//
//import com.dark.spring.spms.entity.User;
//import jakarta.persistence.EntityManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.support.JpaEntityInformation;
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public class UserDaoImpl extends SimpleJpaRepository<User, Integer> implements UserDao {
//
//
//    @Autowired
//    public UserDaoImpl(EntityManager entityManager) {
//        super(User.class, entityManager);
//    }
//
//    public UserDaoImpl(JpaEntityInformation<User, ?> entityInformation, EntityManager entityManager) {
//        super(entityInformation, entityManager);
//    }
//
//    public UserDaoImpl(Class<User> domainClass, EntityManager entityManager) {
//        super(domainClass, entityManager);
//    }
//
//
//    @Override
//    public Optional<User> getUserById(int id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<User> getUserByEmail(String emailId) {
//
//        return Optional.empty();
//    }
//
//}
