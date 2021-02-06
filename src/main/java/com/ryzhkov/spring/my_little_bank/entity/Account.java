package com.ryzhkov.spring.my_little_bank.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private int accountNumber;

    @Column(name = "amount")
    private float amount;

    @Column(name = "opening_date")
    LocalDate openingDate;

    @Column(name = "validity_period")
    LocalDate validityPeriod;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner")
    private User user;

    @OneToMany(cascade = {CascadeType.PERSIST
            , CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
            , mappedBy = "account")
    private List<Transaction> transactions;

    public Account() {
    }

    public Account(float amount, LocalDate openingDate, LocalDate validityPeriod) {
        this.amount = amount;
        this.openingDate = openingDate;
        this.validityPeriod = validityPeriod;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(LocalDate validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    @Override
    public String toString() {
        return "Account{" +
                "amount=" + amount +
                ", openingDate=" + openingDate +
                ", validityPeriod=" + validityPeriod +
                '}';
    }
}
