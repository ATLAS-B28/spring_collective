package com.example.demo_sec.service;

import com.example.demo_sec.CustomUserDetails;
import com.example.demo_sec.entity.User;
import com.example.demo_sec.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(Objects.isNull(user)) {
            System.out.println("User not available");
            throw new UsernameNotFoundException("User not available");
        }

        return new CustomUserDetails(user);
    }
}
