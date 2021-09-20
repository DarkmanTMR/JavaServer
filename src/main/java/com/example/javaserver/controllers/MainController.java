package com.example.javaserver.controllers;


import com.example.javaserver.dao.ClientDAO;
import com.example.javaserver.models.Client;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class MainController {

    private ClientDAO clientDAO;

//    @PostMapping("/upload")
//    public void upload(MultipartFile file){
//        System.out.println((file.getOriginalFilename()));
//        System.out.println(System.getProperty("user.home"));
//        String pathToSaveImage = System.getProperty("user.home") + File.separator + "Desktop" + File.separator;
//        try {
//            file.transferTo(new File(pathToSaveImage + file.getOriginalFilename()));
//        } catch (IOException e){
//            e.printStackTrace();
//        }


//        @PostMapping("/upload")
//                public void upload(@RequestParam String name, MultipartFile file){
//            System.out.println(name);
//        }

//    @PostMapping("/upload")
//    public void upload(@RequestParam String name, MultipartFile file) {
//
//        String pathToSaveImage = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + file.getOriginalFilename();
//        try {
//            file.transferTo(new File(pathToSaveImage));
//            Client s = new Client();
//            s.setName(name);
//            s.setAvatar(pathToSaveImage);
//            clientDAO.save(s);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    @PostMapping("/upload")
    public void upload(@RequestParam String name, MultipartFile file) {

           try {
            file.transferTo(new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + file.getOriginalFilename()));
            Client s = new Client();
            s.setName(name);
            s.setAvatar(file.getOriginalFilename());
            clientDAO.save(s);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }






}



