package com.example.wells_forage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioDTO {

    private Long portfolioId;
    private String portfolioName;
    private String category;
    private Date createdDate;
    private UserDTO user;
    private List<SecurityDTO> securities;

}
