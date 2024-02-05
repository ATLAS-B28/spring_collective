package com.example.springmysql.service;

import com.example.springmysql.dto.UserDTO;
import com.example.springmysql.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO getUserById(Long userId);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(UserDTO user);//as user will be passed in
   void deleteUser(Long userId);
}
