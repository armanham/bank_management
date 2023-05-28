package com.example.model;


public class AccountModel {


    private Long accountId;
    private Long userId;
    private String accountNumber;
    private double balance;
    private String status;
    private String currency;
    private Boolean isDeleted;

    public AccountModel() {
    }

    public AccountModel( Long userId, String accountNumber, double balance,
                         String status, String currency, Boolean isDeleted) {
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}