package com.example.demo_login.controller;

import com.example.demo_login.dto.CustomerDTO;
import com.example.demo_login.entity.Customer;
import com.example.demo_login.security.CustomerDetailsService;
import com.example.demo_login.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private CustomerService customerService;

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        CustomerDTO customerDTO = new CustomerDTO();
        model.addAttribute("user", customerDTO);
        return "register";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        List<CustomerDTO> customerDTOS = customerService.findAllUsers();
        model.addAttribute("users", customerDTOS);
        return "users";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") CustomerDTO customerDTO,
                               BindingResult result,
                               Model model) {
        Customer customerExisting = customerService.findByEmail(customerDTO.getEmail());
        if(customerExisting != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if(result.hasErrors()) {
            model.addAttribute("user", customerDTO);
            return "register";
        }
        customerService.saveUser(customerDTO);
        return "redirect:/register?success";
    }

}


















