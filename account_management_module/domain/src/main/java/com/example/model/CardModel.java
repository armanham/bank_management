package com.example.model;

public class CardModel {


    private Long userId;

    private String cardNumber;


    private String type;

    private double balance;
    private String expirationDate;

    private String cvcCode;

    private String status;

    private String pin;

    private String currency;

    private Boolean isDeleted;

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public CardModel() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvcCode() {
        return cvcCode;
    }

    public void setCvcCode(String cvcCode) {
        this.cvcCode = cvcCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public CardModel(Long userId, String cardNumber, String type, double balance, String expirationDate, String cvcCode, String status, String pin, String currency, Boolean isDeleted) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.type = type;
        this.balance = balance;
        this.expirationDate = expirationDate;
        this.cvcCode = cvcCode;
        this.status = status;
        this.pin = pin;
        this.currency = currency;
        this.isDeleted = isDeleted;
    }
}
