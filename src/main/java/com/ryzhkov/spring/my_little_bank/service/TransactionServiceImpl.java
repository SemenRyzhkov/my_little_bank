package com.ryzhkov.spring.my_little_bank.service;

import com.ryzhkov.spring.my_little_bank.entity.Account;
import com.ryzhkov.spring.my_little_bank.entity.Transaction;
import com.ryzhkov.spring.my_little_bank.repository.AccountRepository;
import com.ryzhkov.spring.my_little_bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Account getAccountById(int accountNumber) {
        Account account = null;
        Optional<Account> accountOptional = accountRepository.findById(accountNumber);
        if (accountOptional.isPresent()) {
            account = accountOptional.get();
        }
        return account;
    }

    @Override
    public void payToAccount(int accountNumber, float amount) {
        Account account = getAccountById(accountNumber);
        float sum = account.getAmount() + amount;
        makeTransaction(account, amount, sum, "pay");
    }

    @Override
    public void withdrawFromAccount(int accountNumber, float amount) {
        Account account = getAccountById(accountNumber);
        float accountAmount = account.getAmount();
        if (accountAmount < amount) {
            throw new RuntimeException("Insufficient funds");
        } else {
            float sum = accountAmount - amount;
            makeTransaction(account, amount, sum, "withdraw");
        }
    }

    @Override
    public void transfer(int accountOriginNumber, int accountDestNumber, float amount) {
        if (accountOriginNumber == accountDestNumber) {
            throw new RuntimeException(
                    "Impossible operation: account id must be different");
        }
        withdrawFromAccount(accountOriginNumber, amount);
        payToAccount(accountDestNumber, amount);
    }

    @Override
    public List<Transaction> getAccountOperationByPeriod(int accountNumber, LocalDateTime start, LocalDateTime end) {
        return transactionRepository
                .getTransactionsByAccount_AccountNumber(accountNumber)
                .stream()
                .filter(tr -> isBetweenHalfOpen(tr.getDateTime(), start, end))
                .collect(Collectors.toList());
    }

    private boolean checkValidityPeriod(LocalDate localDate) {
        return localDate.compareTo(LocalDate.now()) > 0;
    }

    private void makeTransaction(Account account, float amount, float sum, String type) {
        if (account == null) {
            throw new RuntimeException("Enter correct account number");
        } else if (!checkValidityPeriod(account.getValidityPeriod())) {
            throw new RuntimeException("Validity period is overdue");
        } else {
            Transaction transaction = new Transaction(type,
                    LocalDateTime.now(), amount);
            account.addTransaction(transaction);
            account.setAmount(sum);
            accountRepository.save(account);
        }
    }

    private static boolean isBetweenHalfOpen(LocalDateTime ld, LocalDateTime startDate, LocalDateTime endDate) {
        return ld.compareTo(startDate) >= 0 && ld.compareTo(endDate) <= 0;
    }
}
