package com.example.bank.service;

import com.example.bank.dto.AccountDto;
import com.example.bank.dto.TransactionDto;
import com.example.bank.dto.TransferFundDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto account);

    AccountDto getAccountById(Long id);

    AccountDto depositMoney(Long id, double amount);

    AccountDto withdrawMoney(Long id, double amount);

    List<AccountDto> getAllAmounts();

    AccountDto deleteAccount(Long id);

    void transferFunds(TransferFundDto transferFundDto);

    List<TransactionDto> getInfoByIdOrderByDateDesc(Long accountId);
}
