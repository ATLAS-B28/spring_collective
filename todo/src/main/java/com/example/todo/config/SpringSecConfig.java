package com.example.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecConfig {

    //passoword encoder
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //here we can define beans
    //here we configure a security filter chain
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //HttpSecurity It allows configuring web-based security for
        // specific http requests. By default, it will be applied to all requests,
        // but can be restricted using #requestMatcher(RequestMatcher) or other similar methods.
        httpSecurity.csrf(Customizer.withDefaults())//Csrf - Enables CSRF protection. This is activated by default when using EnableWebSecurity.
                    .authorizeHttpRequests(//Allows restricting access based upon the HttpServletRequest
                            // using RequestMatcher implementations (i.e., via URL patterns).
                            (authorize) -> {
                                authorize.requestMatchers(HttpMethod.POST, "/api/v1/todos/create-todo").hasRole("ADMIN");
                                authorize.anyRequest().authenticated();
                                //anyRequest - Maps any request.
                                //Returns:
                                //the object that is chained after creating the RequestMatcher
                                //authenticated - Specify that URLs are allowed by any authenticated user.
                                //Returns:
                                //the AuthorizeHttpRequestsConfigurer<H extends HttpSecurityBuilder<H>>.AuthorizationManagerRequestMatcherRegistry for further customization

                    })
                    .httpBasic(Customizer.withDefaults());
        //httpBasic - Configures HTTP Basic authentication

        return httpSecurity.build();
    }
    //customize the user entity
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.builder()
                .username("aditya")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
