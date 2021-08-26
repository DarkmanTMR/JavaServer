package com.example.javaserver.controllers;


import com.example.javaserver.dao.CarDAO;
import com.example.javaserver.models.Car;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class MainController {



    private CarDAO carDAO;

    public MainController(CarDAO carDAO) {
        this.carDAO = carDAO;
    }


    @GetMapping("/cars")
    public List<Car> cars() {
        List<Car> all = carDAO.findAll();
        return all;
    }

        @PostMapping("/car")
        public void saveCar(@RequestBody Car car) {
            System.out.println(car);
            carDAO.save(car);

        }
    }


