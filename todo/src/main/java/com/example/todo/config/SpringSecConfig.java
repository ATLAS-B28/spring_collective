package com.example.todo.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//now we will be using @EnableMethodSecurity
//bean-based configuration for the authorization types.
@EnableMethodSecurity // enables @preauthorized
@Configuration
@AllArgsConstructor
public class SpringSecConfig {

    private UserDetailsService userDetailsService;
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
        httpSecurity.csrf(csrf -> csrf.disable())//Csrf - Enables CSRF protection. This is activated by default when using EnableWebSecurity.
                //alternative - AbstractHttpConfigurer::disable
                    .authorizeHttpRequests(//Allows restricting access based upon the HttpServletRequest
                            // using RequestMatcher implementations (i.e., via URL patterns).
                            (authorize) -> {
                            /*   authorize.requestMatchers(HttpMethod.POST,"/api/v1/todos/create-todo").hasRole("ADMIN");
                            //    authorize.requestMatchers(HttpMethod.GET,"/api/v1/todos/all").hasAnyRole("ADMIN","USER");
                            //    authorize.requestMatchers(HttpMethod.GET,"/api/v1/todos/get/{id}").hasAnyRole("ADMIN","USER");
                            //    authorize.requestMatchers(HttpMethod.DELETE,"/api/v1/todos/delete/{id}").hasRole("ADMIN");
                            //    authorize.requestMatchers(HttpMethod.PUT,"/api/v1/todos/update/{id}").hasRole("ADMIN");
                            //    authorize.requestMatchers(HttpMethod.PATCH,"/api/v1/todos/update-status/{id}").hasRole("ADMIN");
                            //    authorize.requestMatchers(HttpMethod.PATCH,"/api/v1/todos/update-status-incomplete/{id}").hasRole("ADMIN");
                              */
                                authorize.requestMatchers("/api/v1/auth/register").permitAll();
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


    //the user details will automatically be passed to auth manager
    //after it is injected and has called the load user function

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    //customize the user entity
    /*@Bean
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
        //in-memory authentication -> stores users in memory
        //using the configuration and spring security filter chain
        //we have also used - database authentication
        //to store users in a database along with their roles,
        //which is a separate role model in a database
    }*/
}
