package com.example.bank.controller;

import com.example.bank.dto.AccountDto;
import com.example.bank.dto.TransactionDto;
import com.example.bank.dto.TransferFundDto;
import com.example.bank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account/")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("create-account")
    //add the annotation request body to extract data and pass to the service
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("{id}/deposit")
    public ResponseEntity<AccountDto> depositMoney(@PathVariable Long id,
                                                   @RequestBody Map<String, Double> request
    ) {
        Double amountKey = request.get("amount");
        //as the amount term serves as a key in the json terms, we pass
        //it and use it to access the value that is the value
        return ResponseEntity.ok(accountService.depositMoney(id, amountKey));
    }

    @PutMapping("{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawMoney(@PathVariable Long id,
                                                    @RequestBody Map<String, Double> request
    ) {
        Double amountKey = request.get("amount");
        return ResponseEntity.ok(accountService.withdrawMoney(id, amountKey));
    }

    @GetMapping("all-amounts")
    public ResponseEntity<List<AccountDto>> getAllAmounts() {
        return ResponseEntity.ok(accountService.getAllAmounts());
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }

    @PostMapping("transfer-funds")
    public ResponseEntity<String> transferFunds(@RequestBody TransferFundDto transferFundDto) {
        accountService.transferFunds(transferFundDto);
        return ResponseEntity.ok("Transfer successful");
    }

    @GetMapping("{id}/transaction-history")
    public ResponseEntity<List<TransactionDto>> getTransactionHistory(@PathVariable Long id) {
        List<TransactionDto> transactionList = accountService.getInfoByIdOrderByDateDesc(id);
        return ResponseEntity.ok(transactionList);
    }
}
