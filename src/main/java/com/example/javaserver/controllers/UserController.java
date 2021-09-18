package com.example.javaserver.controllers;

import com.example.javaserver.dao.UserDAO;
import com.example.javaserver.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RequestMapping("/users")                                   //для спрощення пропису адреси ("/users" -> "")
public class UserController {

    private UserDAO userDAO;

    @PostMapping("/registration")
    public void registration(@RequestBody User user){
        System.out.println(user);
        byte[] secret = "boom".getBytes();
        String jwtoken = Jwts.builder()
                        .setSubject(user.getName())
                        .signWith(SignatureAlgorithm.HS512, secret)
                                .compact();
                System.out.println(jwtoken);

        user.setActivationToken(jwtoken);
        userDAO.save(user);

//                String subject = Jwts.parser()
//                        .setSigningKey(secret)
//                        .parseClaimsJws(jwtoken)
//                        .getBody()
//                        .getSubject();
//                System.out.println(subject);

    }

    @GetMapping("/activate/{token}")
    public void activate (@PathVariable String token){
        System.out.println(token);
//        User user = userDAO.findByActivationToken(token);
        User user = userDAO.userByToken(token);
    if (!user.isActivated()){
        user.setActivated(true);
        userDAO.save(user);
    }

        System.out.println(user);
    }

    @PostMapping("/login")
    public void login(@RequestBody User requestUser){
        System.out.println(requestUser);
        User user = userDAO.findByNameAndPassword(requestUser.getName(), requestUser.getPassword());
        System.out.println(user);
    }
}
// TODO: 18.09.2021 38:50 