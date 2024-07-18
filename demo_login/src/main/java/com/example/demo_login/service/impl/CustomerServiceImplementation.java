package com.example.demo_login.service.impl;

import com.example.demo_login.dto.CustomerDTO;
import com.example.demo_login.entity.Customer;
import com.example.demo_login.entity.Role;
import com.example.demo_login.repository.CustomerRepository;
import com.example.demo_login.repository.RoleRepository;
import com.example.demo_login.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomerServiceImplementation implements CustomerService {

    private CustomerRepository customerRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(CustomerDTO customerDTO) {
         Customer customer = new Customer();
         customer.setName(customerDTO.getFirstName() + " " + customerDTO.getLastName());
         customer.setEmail(customerDTO.getEmail());
         //encryption
         customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
         Role role = roleRepository.findByName("ROLE_ADMIN");
         if(role == null) {
             role = checkRoleExists();
         }
         customer.setRoles(List.of(role));
         customerRepository.save(customer);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<CustomerDTO> findAllUsers() {

        return customerRepository
                .findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    //mappings
    private CustomerDTO convertEntityToDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        String[] name = customer.getName().split("");
        customerDTO.setFirstName(name[0]);
        customerDTO.setLastName(name[1]);
        customerDTO.setEmail(customer.getEmail());
        return customerDTO;
    }
    //checking roles
    private Role checkRoleExists( ) {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

}
