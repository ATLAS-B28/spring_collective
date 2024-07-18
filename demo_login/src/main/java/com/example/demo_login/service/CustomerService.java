package com.example.demo_login.service;

import com.example.demo_login.dto.CustomerDTO;
import com.example.demo_login.entity.Customer;

import java.util.List;

public interface CustomerService {
    void saveUser(CustomerDTO customerDTO);

    Customer findByEmail(String email);

    List<CustomerDTO> findAllUsers();
}
