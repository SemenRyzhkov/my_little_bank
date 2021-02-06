package com.ryzhkov.spring.my_little_bank.service;

import com.ryzhkov.spring.my_little_bank.repository.UserRepository;
import com.ryzhkov.spring.my_little_bank.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userDao;


    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getById(int id) {
        User user = null;
        Optional<User>userOptional = userDao.findById(id);
        if (userOptional.isPresent()){
            user = userOptional.get();
        }
        return user;
    }

    @Override
    public User getByName(String fullName) {
        return userDao.getByFullName(fullName);
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return userDao.getByPhone(phoneNumber);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }


}
