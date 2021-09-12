package com.example.javaserver.controllers;


import com.example.javaserver.dao.UserDAO;
import com.example.javaserver.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class MainController {

    private UserDAO userDAO;


    @GetMapping("/users")
    public List<User> users() {
        List<User> users = userDAO.findAll();
        return users;
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        userDAO.save(user);
        return user;


    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        return userDAO.findById(id).get();
    }

    @PatchMapping("/users")
    public User updateUser(@RequestBody User userFromrequest) {
        User userFromDb = userDAO.getById(userFromrequest.getId());
        userDAO.save(userFromrequest);
        return userFromrequest;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userDAO.deleteById(id);
    }

    @PostMapping("/saveUserWithCar")
    public void saveUserWIthCar(@RequestBody User user) {
        System.out.println(user);


    }

}
