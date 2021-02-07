package com.ryzhkov.spring.my_little_bank.controller;

import com.ryzhkov.spring.my_little_bank.entity.Account;
import com.ryzhkov.spring.my_little_bank.entity.User;
import com.ryzhkov.spring.my_little_bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("users/{id}")
    public User getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("users/full_name/{fullName}")
    public User getByName(@PathVariable String fullName) {
        return service.getByName(fullName);
    }

    @GetMapping("users/phone_number/{phoneNumber}")
    public User getByPhoneNumber(@PathVariable String phoneNumber) {
        return service.getByPhoneNumber(phoneNumber);
    }

    @GetMapping("users/email/{email}")
    public User getByEmail(@PathVariable String email) {
        return service.getByEmail(email);
    }

    @PostMapping("/users")
    public User add(@RequestBody User user) {
        service.save(user);
        return user;
    }

    @PutMapping("/users")
    public User update(@RequestBody User user) {
        service.save(user);
        return user;
    }

    @GetMapping("/users/accounts/{id}")
    public List<Account> showAllAccounts(@PathVariable int id) {
        return service.getAllAccounts(id);
    }

    @PostMapping("/users/accounts/add/{userId}")
    public Account addAccount(@RequestBody Account account, @PathVariable int userId) {
        service.saveAccount(account, userId);
        return account;
    }

    @DeleteMapping("/users/accounts/delete/{userId}/{accountNumber}")
    public String deleteAccount(@PathVariable int userId, @PathVariable int accountNumber) {
        service.deleteAccount(userId, accountNumber);
        return "Account with number = " + accountNumber + " was deleted";
    }
}

