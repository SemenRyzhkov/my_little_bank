package com.ryzhkov.spring.my_little_bank.service;

import com.ryzhkov.spring.my_little_bank.entity.Account;
import com.ryzhkov.spring.my_little_bank.entity.Transaction;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface TransactionService {

    Account getAccountById(int accountId);

    void payToAccount(int accountNumber, float amount);

    void withdrawFromAccount(int accountNumber, float amount);

    void transfer(int accountOriginNumber, int accountDestNumber, float amount);

    Collection<Transaction> getAccountOperationByPeriod(int accountId, LocalDateTime start, LocalDateTime end);

}
