package com.example.demo_login.security;

import com.example.demo_login.entity.Customer;
import com.example.demo_login.entity.Role;
import com.example.demo_login.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailsService implements UserDetailsService {

    //repository
    private CustomerRepository customerRepository;

    private Collection<? extends GrantedAuthority> mapRolesToAuthority(Collection<Role> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByEmail(email);

        if(customer != null) {
            //User -> getting username, password and roles collection and build it
            return new User(
                    customer.getEmail(),
                    customer.getPassword(),
                    mapRolesToAuthority(
                            customer.getRoles()
                    )
            );
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}

