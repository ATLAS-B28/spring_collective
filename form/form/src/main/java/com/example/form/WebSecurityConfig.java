package com.example.form;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /*
        * httpSecurity is an instance of HttpSecurity,
        * which is the main class used for configuring the
        * security settings of an HTTP application in Spring Security
        * */
        httpSecurity
                .authorizeHttpRequests(//<-httpSecurity to configure authorization rules for incoming HTTP request
                        (req) ->//<-which allows you to define custom authorization rules based on the request.
                                req.requestMatchers("/","/home")
                                        //the authorization rules defined
                                        //afterward should only apply to requests matching
                                        //the root or home path
                                .permitAll()
                                        //allows all requests to proceed unrestricted
                                .anyRequest()
                                .authenticated()
                                 //if any other request is made, it must be authenticated
                )
                .formLogin(//used to configure form-based login
                        (form) ->//form parameter represents the login form config object
                                form.loginPage("/login")//specifies the login page URL
                                .permitAll()//allows unauthenticated users to access the login page
                )
                .logout(
                        LogoutConfigurer::permitAll
                        //logout configure is a method reference
                );

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // An interface to load user-specific data during the authentication process
        /*
        * It has a single method called loadUserByUsername
        * which takes a username as an argument and returns
        * an implementation of the UserDetails interface.
        * It is responsible for retrieving user details
        * (such as username, password, and authorities)
        * from a data source (e.g., database) based on the provided username.
        * */
        UserDetails user = // the interface represents core user information retrieved by the UserDetailsService.
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
