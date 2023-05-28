package com.example.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@Column(name = "sender_id")
    private Long senderId;

@Column(name = "receiver_id")
    private Long receiverId;

    @Column(name = "transaction_number")
    private String transactionNumber;

    @NotNull
    @Column(name = "transaction_type")
    private String transactionType;

    @NotNull
    @Column(name = "sender")
    private String sender;

    @NotNull
    @Column(name = "receiver")
    private String receiver;


    @NotNull
    @Column(name = "date")
    private String date;

    @NotNull
    @Column (name = "balance")
    private double balance;

    @NotNull
    @Column(name = "currency")
    private String currency;





    public TransactionEntity(Long senderId, Long receiverId, String transactionNumber, String transactionType, String sender, String receiver, String date, double balance, String currency) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.transactionNumber = transactionNumber;
        this.transactionType = transactionType;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.balance = balance;
        this.currency = currency;
    }

    public TransactionEntity() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }


    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }


    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }


    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
