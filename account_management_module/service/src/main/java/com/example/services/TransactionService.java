package com.example.services;


import com.example.entity.AccountEntity;
import com.example.entity.CardEntity;
import com.example.entity.TransactionEntity;
import com.example.enums.TransactionType;
import com.example.repository.AccountRepository;
import com.example.repository.CardRepository;
import com.example.repository.TransactionRepository;
import com.example.services.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final CardService cardService;

    private final CardRepository cardRepository;

    private final AccountService accountService;

    private final AccountRepository accountRepository;

    Validation validation = new Validation();


    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CardRepository cardRepository, CardService cardService, CardRepository cardRepository1, AccountService accountService, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.cardService = cardService;
        this.cardRepository = cardRepository1;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }


    public Optional<TransactionEntity> get(String transactionNumber) {
        return transactionRepository.findTransactionEntityByTransactionNumber(transactionNumber);
    }

    public List<TransactionEntity> getAll() {
        return transactionRepository.findAll();
    }


    public String transaction(TransactionEntity te) {
        switch (TransactionType.valueOf(te.getTransactionType())) {
            case CARD_TO_CARD:
                return cardToCard(te);
            case CARD_TO_ACCOUNT:
                return cardToAccount(te);
            case ACCOUNT_TO_CARD:
                return accountToCard(te);
            case ACCOUNT_TO_ACCOUNT:
                return accountToAccount(te);
        }
        return "Not valid transaction";
    }


    public String cardToCard(TransactionEntity te) {

        if (cardRepository.findCardEntityByCardNumber(te.getSender()).isPresent() &&
                cardRepository.findCardEntityByCardNumber(te.getReceiver()).isPresent()) {
            Optional<CardEntity> cardEntity1 = cardRepository.findCardEntityByCardNumber(te.getSender());
            Optional<CardEntity> cardEntity2 = cardRepository.findCardEntityByCardNumber(te.getReceiver());

            if (te.getBalance() <= cardEntity1.get().getBalance() && te.getBalance() > 0) {
                te.setCurrency(cardEntity1.get().getCurrency());
                double balance = validation.getRate(te.getCurrency(),
                        cardEntity2.get().getCurrency(), te.getBalance());
                cardService.withdrawBalance(cardEntity1.get().getCardNumber(), te.getBalance());
                cardService.rechargeBalance(cardEntity2.get().getCardNumber(), balance);
                te.setSenderId(cardEntity1.get().getUserId());
                te.setReceiverId(cardEntity2.get().getUserId());
                te.setDate(validation.validateDateForTransaction());
                te.setTransactionNumber(validation.generateTransactionNumber());
                transactionRepository.save(te);
            } else return "You don't have enough money to complete the transaction";
        } else return "Not valid card number";
        return "Transaction completed successfully";
    }


    public String cardToAccount(TransactionEntity te) {
        if (cardRepository.findCardEntityByCardNumber(te.getSender()).isPresent() &&
                accountRepository.findAccountEntityByAccountNumber(te.getReceiver()).isPresent()) {
            Optional<CardEntity> cardEntity = cardRepository.findCardEntityByCardNumber(te.getSender());
            Optional<AccountEntity> accountEntity = accountRepository.findAccountEntityByAccountNumber(te.getReceiver());
            if (te.getBalance() <= cardEntity.get().getBalance() && te.getBalance() > 0) {
                te.setCurrency(cardEntity.get().getCurrency());
                double balance = validation.getRate(te.getCurrency(),
                        accountEntity.get().getCurrency(), te.getBalance());
                cardService.withdrawBalance(cardEntity.get().getCardNumber(), te.getBalance());
                accountService.rechargeBalance(accountEntity.get().getAccountNumber(), balance);
                te.setSenderId(cardEntity.get().getUserId());
                te.setReceiverId(accountEntity.get().getUserId());
                te.setDate(validation.validateDateForTransaction());
                te.setTransactionNumber(validation.generateTransactionNumber());
                transactionRepository.save(te);
            } else return "You don't have enough money to complete the transaction";
        } else return "Not valid card number or account number";
        return "Transaction completed successfully";
    }


    public String accountToCard(TransactionEntity te) {
        if (cardRepository.findCardEntityByCardNumber(te.getReceiver()).isPresent() &&
                accountRepository.findAccountEntityByAccountNumber(te.getSender()).isPresent()) {
            Optional<CardEntity> cardEntity = cardRepository.findCardEntityByCardNumber(te.getReceiver());
            Optional<AccountEntity> accountEntity = accountRepository.findAccountEntityByAccountNumber(te.getSender());
            if (te.getBalance() <= accountEntity.get().getBalance() && te.getBalance() > 0) {
                te.setCurrency(accountEntity.get().getCurrency());
                double balance = validation.getRate(te.getCurrency(),
                        cardEntity.get().getCurrency(), te.getBalance());
                accountService.withdrawBalance(accountEntity.get().getAccountNumber(), te.getBalance());
                cardService.rechargeBalance(cardEntity.get().getCardNumber(), balance);
                te.setSenderId(accountEntity.get().getUserId());
                te.setReceiverId(cardEntity.get().getUserId());
                te.setDate(validation.validateDateForTransaction());
                te.setTransactionNumber(validation.generateTransactionNumber());
                transactionRepository.save(te);
            } else return "You don't have enough money to complete the transaction";
        } else return "Not valid account/card number";
        return "Transaction completed successfully";
    }

    String accountToAccount(TransactionEntity te) {
        if (accountRepository.findAccountEntityByAccountNumber(te.getSender()).isPresent()
                && accountRepository.findAccountEntityByAccountNumber(te.getReceiver()).isPresent()) {
            Optional<AccountEntity> accountEntity1 = accountRepository.findAccountEntityByAccountNumber(te.getReceiver());
            Optional<AccountEntity> accountEntity2 = accountRepository.findAccountEntityByAccountNumber(te.getSender());
            if (te.getBalance() <= accountEntity1.get().getBalance() && te.getBalance() > 0) {
                te.setCurrency(accountEntity1.get().getCurrency());
                double balance = validation.getRate(te.getCurrency(),
                        accountEntity2.get().getCurrency(), te.getBalance());
                accountService.withdrawBalance(accountEntity1.get().getAccountNumber(), te.getBalance());
                accountService.rechargeBalance(accountEntity2.get().getAccountNumber(), balance);
                te.setSenderId(accountEntity1.get().getUserId());
                te.setReceiverId(accountEntity2.get().getUserId());
                te.setDate(validation.validateDateForTransaction());
                te.setTransactionNumber(validation.generateTransactionNumber());
                transactionRepository.save(te);
            } else return "You don't have enough money to complete the transaction";
        } else return "Not valid account number";
        return "Transaction completed successfully";
    }


}
