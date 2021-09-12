package com.example.javaserver.models;

import lombok.*;

import javax.persistence.*;

@Table(name = "User")
@Entity
@Getter
@Setter
@ToString(exclude = {"car"})
@NoArgsConstructor

public class User {
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Car car;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(int id, String name, Car car) {
        this.id = id;
        this.name = name;
        this.car = car;
    }

    public User(String name) {
        this.name = name;
    }

}