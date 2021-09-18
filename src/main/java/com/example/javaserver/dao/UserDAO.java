package com.example.javaserver.dao;

import com.example.javaserver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDAO extends JpaRepository<User, Integer> {

//    User findByActivationToken(String activationToken);                             //чим простіше тим краще))


    @Query("select u from User u where u.activationToken=:activationToken")             // більш динамічний варіант
    User userByToken(@Param("activationToken")String activationToken);



    User findByNameAndPassword(String name, String password);

    boolean existsUserByLoginToken(String loginToken);
}

