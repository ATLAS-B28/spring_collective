package com.example.bank.dto;
//use data annotation

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/*@Data
@AllArgsConstructor

public class AccountDto {
    private Long id;
    private String accountHolderName;
    private double balance;

}*/

public record AccountDto(
        Long id,
        String accountHolderName,
        double balance
) {
}