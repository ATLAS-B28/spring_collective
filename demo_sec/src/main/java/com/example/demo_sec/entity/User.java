package com.example.demo_sec.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {

    @Id
    private Integer id;
    private String userName;
    private String password;
}
