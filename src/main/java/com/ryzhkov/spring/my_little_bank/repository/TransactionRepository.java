package com.ryzhkov.spring.my_little_bank.repository;

import com.ryzhkov.spring.my_little_bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
     List<Transaction> getTransactionsByAccount_AccountNumber(int accountNumber);
}
