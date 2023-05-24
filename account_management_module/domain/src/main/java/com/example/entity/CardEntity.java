package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "card")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "card_number")
    private String cardNumber;

    @NotNull
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "balance")
    private double balance;

    @NotNull
    @Column(name = "expiration_date")
    private String expirationDate;

    @NotNull
    @Column(name = "cvc_code")
    private String cvcCode;

    @NotNull
    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "pin")
    private String pin;

    @NotNull
    @Column(name = "currency")
    private String currency;


    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public CardEntity() {
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Long getCardId() {
        return cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
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

    public CardEntity(Long userId, String cardNumber, String type, Double balance, String expirationDate, String cvcCode, String status, String pin, String currency, Boolean isDeleted) {
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


    public CardEntity(String type, String pin, String currency) {
        this.type = type;
        this.pin = pin;
        this.currency = currency;
    }
}
