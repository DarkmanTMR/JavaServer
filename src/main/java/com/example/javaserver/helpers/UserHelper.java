package com.example.javaserver.helpers;

import com.example.javaserver.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {
    private byte[] secret = "boom".getBytes();
    public String tokenizer (User user, String secret){
        return Jwts.builder()
                .setSubject(user.getName())
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }
}
