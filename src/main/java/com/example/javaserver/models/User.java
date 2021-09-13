package com.example.javaserver.models;

import lombok.*;

import javax.persistence.*;

@Table(name = "User")
@Entity
@Getter
@Setter
@NoArgsConstructor

public class User {
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private String email;
    private boolean isActivated = false;


    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;

    }


    public User(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public User(String name) {
        this.name = name;
    }


}