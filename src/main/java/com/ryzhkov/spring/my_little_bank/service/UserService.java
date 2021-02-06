package com.ryzhkov.spring.my_little_bank.service;

import com.ryzhkov.spring.my_little_bank.entity.User;

import java.util.List;

public interface UserService {
   List<User> getAllUsers();

   User getById(int id);

   User getByName(String fullName);

   User getByPhoneNumber(String phoneNumber);

   User getByEmail(String email);

   void save(User user);


}
