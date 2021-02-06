package com.ryzhkov.spring.my_little_bank.repository;

import com.ryzhkov.spring.my_little_bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getByFullName(String fullName);

    User getByPhone(String phoneNumber);

    User getByEmail(String email);

}
