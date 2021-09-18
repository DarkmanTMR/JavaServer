package com.example.javaserver.controllers;

import com.example.javaserver.dao.UserDAO;
import com.example.javaserver.helpers.UserHelper;
import com.example.javaserver.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RequestMapping("/users")                                   //для спрощення пропису адреси ("/users" -> "")
public class UserController {

    private UserDAO userDAO;
    private UserHelper userHelper;

    @GetMapping("")
    public List<User>getUsers(HttpServletRequest httpServletRequest){
            String authenticationHeader = httpServletRequest.getHeader("Authentication");
            System.out.println(authenticationHeader);
           String bearer =  authenticationHeader.replace("Bearer ", "");
            System.out.println(bearer);
            System.out.println(userDAO.existsUserByLoginToken(bearer));

            return null;
    }

    @PostMapping("/registration")
    public void registration(@RequestBody User user){
        System.out.println(user);



        user.setActivationToken(userHelper.tokenizer(user,"boom"));
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

//    @PostMapping("/login")
//    public void login(@RequestBody User requestUser, HttpServletResponse response){
//
//        User user = userDAO.findByNameAndPassword(requestUser.getName(), requestUser.getPassword());
//        String bambamToken = userHelper.tokenizer(user,"bambam");
//        user.setLoginToken(bambamToken);
//       userDAO.save(user);
//
//       response.addHeader("xxx",bambamToken);
//    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User requestUser){
        User user = userDAO.findByNameAndPassword(requestUser.getName(), requestUser.getPassword());
        String bambamToken = userHelper.tokenizer(user,"bambam");
        user.setLoginToken(bambamToken);
       userDAO.save(user);
       if (user != null) {
           HttpHeaders httpHeaders = new HttpHeaders();
           httpHeaders.add("token", bambamToken);
           ResponseEntity<String> responseEntity = new ResponseEntity<>("login is fine", httpHeaders, HttpStatus.OK);
           return responseEntity;
       }
       return new ResponseEntity<String>("wrong credentials", HttpStatus.UNAUTHORIZED);

    }
}
