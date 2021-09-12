package com.example.javaserver.dao;

import com.example.javaserver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
}
