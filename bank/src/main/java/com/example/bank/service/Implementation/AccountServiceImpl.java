package com.example.bank.service.Implementation;

import com.example.bank.dto.AccountDto;
import com.example.bank.dto.TransactionDto;
import com.example.bank.dto.TransferFundDto;
import com.example.bank.entity.Account;
import com.example.bank.entity.Transaction;
import com.example.bank.exception.AccountException;
import com.example.bank.mapper.AccountMapper;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.TransactionRepository;
import com.example.bank.service.AccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
   //call in repository and create a constructor for the service class

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public String TRANSACTION_TYPE_DEPOSIT = "DEPOSIT";
    public String TRANSACTION_TYPE_WITHDRAW = "WITHDRAW";
    public String TRANSACTION_TYPE_TRANSFER = "TRANSFER";

    public AccountServiceImpl(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository
    ) {

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;

    }

    @Override//we have to send a dto and present it to the user in the same format
    //and also convert the dto to entity save it and then do the presenting
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow( () -> new AccountException("Account not found"));

        return AccountMapper.mapToAccountDto(account);

    }

    @Override
    public AccountDto depositMoney(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow( () -> new AccountException("Account not found"));

        //get the balance to add the new amount to it
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        Account savedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(
                TRANSACTION_TYPE_DEPOSIT
                );
        transaction.setTimeStamp(LocalDateTime.now());

        //accountRepository.save(account);
        transactionRepository.save(transaction);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdrawMoney(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow( () -> new AccountException("Account not found"));

        if(account.getBalance() < amount) {
            throw new RuntimeException("Insufficient Balance");
        }
        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        Account savedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(
                TRANSACTION_TYPE_WITHDRAW
                );
        transaction.setTimeStamp(LocalDateTime.now());

        transactionRepository.save(transaction);

        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public List<AccountDto> getAllAmounts() {
        List<Account> account = accountRepository.findAll();
        return account.stream().map(AccountMapper::mapToAccountDto).toList();
    }

    @Override
    public AccountDto deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow( () -> new AccountException("Account not found"));

        accountRepository.deleteById(id);

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public void transferFunds(TransferFundDto transferFundDto) {
        //retrieve the account from the database
        Account fromAccount = accountRepository.findById(transferFundDto.fromAccountId())
                .orElseThrow( () -> new AccountException("Account not found"));

        Account toAccount = accountRepository.findById(transferFundDto.toAccountId())
                .orElseThrow( () -> new AccountException("Account not found"));

        //debit the amount from the 1st
        fromAccount.setBalance(fromAccount.getBalance() - transferFundDto.amount());
        toAccount.setBalance(toAccount.getBalance() + transferFundDto.amount());

        Transaction transaction = new Transaction();
        transaction.setAccountId(transferFundDto.fromAccountId());
        transaction.setAmount(transferFundDto.amount());
        transaction.setTransactionType(
                TRANSACTION_TYPE_TRANSFER
                );
        transaction.setTimeStamp(LocalDateTime.now());
        transactionRepository.save(transaction);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    @Override
    public List<TransactionDto> getInfoByIdOrderByDateDesc(Long accountId) {

        List<Transaction> transactions = transactionRepository.findByAccountIdOrderByTimeStampDesc(accountId);

        return transactions.stream().map(this::convertToDto).toList();
    }

    //map to dto
    private TransactionDto convertToDto(Transaction transaction) {

        return new TransactionDto(
            transaction.getId(),
            transaction.getAccountId(),
            transaction.getAmount(),
            transaction.getTransactionType(),
            transaction.getTimeStamp()
        );
    }


}
