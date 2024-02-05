package com.example.springmysql.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @Schema(description = "First name of the user")
    @NotEmpty(message = "First name is required") // not null and not empty
    private String firstName;

    @Schema(description = "Last name of the user")
    @NotEmpty
    private String lastName;

    @Schema(description = "Email of the user")
    @NotEmpty
    @Email(message = "Invalid email")//valid email address
    private String email;
    //don't add sensitive information in the DTO
    //like we did in jpa entity
}
