package com.example.demo_login.config;

import com.example.demo_login.security.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSec {
   //call the customer details and service
    @Autowired
    private CustomerDetailsService customerDetailsService;

    //bean for bcrypt
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //security chain for login and call http and
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->//org. springframework. security. config. annotation. web. configurers. AuthorizeHttpRequestsConfigurer. AuthorizationManagerRequestMatcherRegistry
                         auth.requestMatchers("/register/**").permitAll()
                                 .requestMatchers("/index").permitAll()
                                 .requestMatchers("users").hasRole("ADMIN")
                ).formLogin(//org. springframework. security. config. annotation. web. configurers. FormLoginConfigurer<HttpSecurity>
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/users")
                                .permitAll()
                ).logout(//org. springframework. security. config. annotation. web. configurers. LogoutConfigurer<HttpSecurity>
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                ).build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customerDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}

















