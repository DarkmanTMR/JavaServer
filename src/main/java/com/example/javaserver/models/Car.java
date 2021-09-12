package com.example.javaserver.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"user"})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private User user;

    public Car(String model) {
        this.model = model;
    }

    public Car(int id, String model, User user) {
        this.id = id;
        this.model = model;
        this.user = user;
    }
}