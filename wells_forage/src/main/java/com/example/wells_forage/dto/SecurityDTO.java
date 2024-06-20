package com.example.wells_forage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityDTO {

    private int sec_id;
    private String sec_name;
    private String category;
    private double purchasePrice;
    private Date purchaseDate;
    private PortfolioDTO portfolio;

}
