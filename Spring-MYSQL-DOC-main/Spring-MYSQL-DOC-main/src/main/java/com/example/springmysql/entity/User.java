package com.example.springmysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity//annotates this class as jpa entity
@Table(name="users")//configures the tables and schemas, etc
public class User {
    //primary key annotation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //<- these are strategies for primary key generation
    //there are many others too
    //user jpa entity class has a primary key
    @Column(nullable = false)//configures the column
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
}
