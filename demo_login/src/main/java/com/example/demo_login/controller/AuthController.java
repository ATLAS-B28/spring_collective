package com.example.demo_login.controller;

import com.example.demo_login.dto.CustomerDTO;
import com.example.demo_login.entity.Customer;
import com.example.demo_login.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class AuthController {

    private CustomerService customerService;

    @GetMapping("index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        List<CustomerDTO> customerDTOS = customerService.findAllUsers();
        model.addAttribute("users", customerDTOS);
        return "users";
    }

    @PostMapping("/registration/user")
    public String registration(@Valid @ModelAttribute("users") CustomerDTO customerDTO,
                               BindingResult result,
                               Model model) {
        Customer customerExisting = customerService.findByEmail(customerDTO.getEmail());
        if(customerExisting != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if(result.hasErrors()) {
            model.addAttribute("user", customerDTO);
            return "registration";
        }
        customerService.saveUser(customerDTO);
        return "redirect:/register?success";
    }

}


















