package com.example.springmysql.respository;

import com.example.springmysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    //JpaRepository is an interface, takes in Type and Primary Key Type

}
