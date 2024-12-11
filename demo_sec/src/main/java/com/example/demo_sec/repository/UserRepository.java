package com.example.demo_sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo_sec.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String userName);
}
