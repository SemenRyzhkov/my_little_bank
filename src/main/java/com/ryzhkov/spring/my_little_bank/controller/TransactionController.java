package com.ryzhkov.spring.my_little_bank.controller;

import com.ryzhkov.spring.my_little_bank.entity.Transaction;
import com.ryzhkov.spring.my_little_bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("api")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction/pay/{accountNumber}/{amount}")
    public void pay(@PathVariable int accountNumber, @PathVariable float amount) {
        transactionService.payToAccount(accountNumber, amount);
    }

    @PostMapping("/transaction/withdraw/{accountNumber}/{amount}")
    public void withdraw(@PathVariable int accountNumber, @PathVariable float amount) {
        transactionService.withdrawFromAccount(accountNumber, amount);
    }

    @PostMapping("/transaction/transfer/{accountOriginNumber}/{accountDestNumber}/{amount}")
    public void pay(@PathVariable int accountOriginNumber, @PathVariable int accountDestNumber, @PathVariable float amount) {
        transactionService.transfer(accountOriginNumber, accountDestNumber, amount);
    }

    @GetMapping("/transaction/{accountId}/{start}/{end}")
    public List<Transaction> showAllUsers(@PathVariable int accountId
            , @PathVariable String start, @PathVariable String end) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(start, dateTimeFormatter);
        LocalDateTime endDate = LocalDateTime.parse(end, dateTimeFormatter);
        return (List<Transaction>) transactionService.getAccountOperationByPeriod(accountId, startDate, endDate);
    }
}