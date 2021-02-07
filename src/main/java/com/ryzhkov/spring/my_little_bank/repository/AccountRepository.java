package com.ryzhkov.spring.my_little_bank.repository;

import com.ryzhkov.spring.my_little_bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
