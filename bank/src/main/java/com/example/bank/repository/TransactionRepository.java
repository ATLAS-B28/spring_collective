package com.example.bank.repository;

import com.example.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    //custom method to return a list of transaction
    //by account id and order by time stamp
    //in the descending order
    //also jpa repository creates a query for us
    List<Transaction> findByAccountIdOrderByTimeStampDesc(Long accountId);
}
