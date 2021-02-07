package com.ryzhkov.spring.my_little_bank.service;

import com.ryzhkov.spring.my_little_bank.entity.Account;
import com.ryzhkov.spring.my_little_bank.repository.AccountRepository;
import com.ryzhkov.spring.my_little_bank.repository.UserRepository;
import com.ryzhkov.spring.my_little_bank.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getById(int id) {
        User user = null;
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }
        return user;
    }

    @Override
    public User getByName(String fullName) {
        return userRepository.getByFullName(fullName);
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return userRepository.getByPhone(phoneNumber);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<Account> getAllAccounts(int id) {
        User user = getById(id);
        return user.getAccounts();
    }

    @Override
    public Account getAccountById(int accountId) {
        Account account = null;
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            account = accountOptional.get();
        }
        return account;
    }

    @Override
    public void saveAccount(Account account, int userId) {
        User user = getById(userId);
        user.addAccount(account);
        save(user);
    }

    @Override
    public void deleteAccount(int userId, int accountNumber) {
        User user = getById(userId);
        Account account = getAccountById(accountNumber);
        user.deleteAccount(account);
        save(user);
    }
}
