package com.example.wells_forage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdvisorDTO {

    private Long adv_id;
    private String firstName;
    private String lastName;
    private UserDTO user;
}
