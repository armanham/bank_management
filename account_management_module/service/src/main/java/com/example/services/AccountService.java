package com.example.services;

import com.example.entity.AccountEntity;
import com.example.enums.Currency;
import com.example.enums.Status;
import com.example.model.AccountModel;
import com.example.repository.AccountRepository;
import com.example.services.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountService {

    private final AccountRepository accountRepository;

    Validation validation = new Validation();


    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public void createAccount(AccountEntity accountEntity) {
        accountRepository.save(accountEntity);
    }


    public void createAccount(AccountModel accountModel) {
        String accountNumber = validation.accountValidation(accountModel.getCurrency());
        while (true) {
            if (accountRepository.findAccountEntityByAccountNumber(accountNumber).isPresent()) {
                accountNumber = validation.accountValidation(accountNumber);
            } else break;
        }
        accountModel.setUserId(accountModel.getUserId());
        accountModel.setAccountNumber(accountNumber);
        accountModel.setCurrency(Currency.valueOf(accountModel.getCurrency()).toString());
        accountModel.setStatus(Status.ACTIVE.toString());
        accountModel.setDeleted(false);

        AccountEntity accountEntity = new AccountEntity(accountModel.getUserId(), accountModel.getAccountNumber(),
                accountModel.getBalance(), accountModel.getStatus(), accountModel.getCurrency(), accountModel.getDeleted());
        accountRepository.save(accountEntity);
    }


    public Optional<AccountEntity> getAccount(String accountNumber) {
        return accountRepository.findAccountEntityByAccountNumber(accountNumber);
    }


    public List<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }


    public void updateIsDeleted(String accountNumber) {
        accountRepository.updateIsDeleted(accountNumber);
    }

    public void deactivate(String accountNumber) {
        accountRepository.deactivate(accountNumber);
    }

    public String activate(String accountNumber) {
        Optional<AccountEntity> accountEntity = accountRepository.findAccountEntityByAccountNumber(accountNumber);
        if (!accountEntity.get().getDeleted()) {
            accountRepository.activate(accountNumber);
            return "";
        }
        return "Not valid account number";
    }


    public String block(String accountNumber) {
        Optional<AccountEntity> accountEntity = accountRepository.findAccountEntityByAccountNumber(accountNumber);
        if (!accountEntity.get().getDeleted()) {
            accountRepository.block(accountNumber);
            return "";
        }
        return "Not valid account number";
    }

    public String checkBalance(String accountNumber){
        Optional<AccountEntity> accountEntity = accountRepository.findAccountEntityByAccountNumber(accountNumber);
        return "Your current balance` " +  accountEntity.get().getBalance() + " " + accountEntity.get().getCurrency();
    }


    public String withdrawBalance(String accountNumber, double balance){
        Optional<AccountEntity> accountEntity = accountRepository.findAccountEntityByAccountNumber(accountNumber);
        if (balance <= accountEntity.get().getBalance() && balance >= 1){
            accountEntity.get().setBalance(accountEntity.get().getBalance() - balance);
            accountRepository.save(accountEntity.get());
        } else return "You don't have enough money to complete the transaction";
        return "Transaction completed successfully";
    }


    public String rechargeBalance(String accountNumber,double balance){
        Optional<AccountEntity> accountEntity = accountRepository.findAccountEntityByAccountNumber(accountNumber);
        if (accountEntity.isPresent()) {
            accountEntity.get().setBalance(accountEntity.get().getBalance() + balance);
            accountRepository.save(accountEntity.get());
        }
        return "Your balance has been successfully recharged";
    }
}