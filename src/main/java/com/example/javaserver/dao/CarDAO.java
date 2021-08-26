package com.example.javaserver.dao;

import com.example.javaserver.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDAO extends JpaRepository<Car, Integer> {
}
