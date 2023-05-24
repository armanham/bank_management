package com.example.entity;

import jakarta.persistence.*;


import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;


@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @NotNull
    @Column(name = "user_id")
    private Long userId;
    @NotNull
    @Column(name = "account_number")
    private String accountNumber;


    @Column(name = "is_deleted")
    private boolean isDeleted;
    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "balance")
    private double balance;
    @NotNull
    @Column(name = "currency")
    private String currency;


    public AccountEntity() {
    }

    public AccountEntity(Long userId, String accountNumber, double balance,
                         String status, String currency, boolean isDeleted) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.status = status;
        this.currency = currency;
        this.isDeleted = isDeleted;
    }

    public Long getAccountId() {
        return accountId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}