package com.example.javaserver.controllers;



import com.example.javaserver.dao.ClientDAO;
import com.example.javaserver.models.Client;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class MainController {





        private ClientDAO clientDAO;
    public  MainController(ClientDAO clientDAO){
        this.clientDAO = clientDAO;
    }


        @GetMapping("/clients")
    public List<Client> clients() {
        List<Client> all = clientDAO.findAll();
        return all;
        }

    @PostMapping("/client")
    public void saveClient(@RequestBody Client client) {
        System.out.println(client);
        clientDAO.save(client);

    }
}


