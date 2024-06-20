package com.example.wells_forage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int user_id;
    private String firstName;
    private String lastName;
    private PortfolioDTO portfolioDTO;
    private AdvisorDTO advisor;

}
