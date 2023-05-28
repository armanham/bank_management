package com.example.model;

public class TransactionModel {


    private Long senderId;

    private Long receiverId;

    private String transactionNumber;

    private String transactionType;


    private String sender;

    private String receiver;

    private String date;

    private double balance;

    private String currency;




    public TransactionModel(Long senderId, Long receiverId, String transactionNumber, String transactionType, String sender, String receiver, String date, double balance, String currency) {
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


    public String getCurrency() {
        return currency;
    }
    public double getBalance() {
        return balance;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public TransactionModel() {
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getDate() {
        return date;
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }


}
