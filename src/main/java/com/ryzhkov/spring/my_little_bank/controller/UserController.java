package com.ryzhkov.spring.my_little_bank.controller;

import com.ryzhkov.spring.my_little_bank.entity.User;
import com.ryzhkov.spring.my_little_bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
    public User addEmployee(@RequestBody User user) {
        service.save(user);
        return user;
    }

    @PutMapping("/users")
    public User updateEmployee(@RequestBody User user) {
        service.save(user);
        return user;
    }

}

