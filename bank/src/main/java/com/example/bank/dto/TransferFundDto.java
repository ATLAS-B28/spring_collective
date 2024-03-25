package com.example.bank.dto;

public record TransferFundDto(
        Long fromAccountId,
        Long toAccountId,
        double amount
) {
}
