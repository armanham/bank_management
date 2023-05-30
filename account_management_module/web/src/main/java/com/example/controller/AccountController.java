package com.example.controller;

import com.example.entity.AccountEntity;
import com.example.model.AccountModel;
import com.example.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/create")
    public void createAccount(@RequestBody AccountEntity accountEntity){
        AccountModel accountModel = new AccountModel();
        accountModel.setCurrency(accountEntity.getCurrency());
        accountModel.setUserId(accountEntity.getUserId());
        accountService.createAccount(accountModel);
    }


    @GetMapping("/get/{accountNumber}")
    public Optional<AccountEntity> getAccount(@PathVariable("accountNumber") String accountNumber){
        return accountService.getAccount(accountNumber);
    }

    @GetMapping("/getAll")
    public List<AccountEntity> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @DeleteMapping("/delete/{accountNumber}")
    public void updateIsDeleted(@PathVariable("accountNumber") String accountNumber){
        accountService.updateIsDeleted(accountNumber);
        accountService.deactivate(accountNumber);
    }

    @PutMapping("/activate/{accountNumber}")
    public String activate(@PathVariable("accountNumber") String accountNumber){
        return accountService.activate(accountNumber);
    }

    @PutMapping("/block/{accountNumber}")
    public String block(@PathVariable("accountNumber") String accountNumber){
        return  accountService.block(accountNumber);
    }

    @PutMapping("/deactivate/{accountNumber}")
    public void deactivate(@PathVariable("accountNumber") String accountNumber){
        accountService.deactivate(accountNumber);
    }

@GetMapping("/balance/{accountNumber}")
    public String checkBalance(@PathVariable("accountNumber") String accountNumber){
        return accountService.checkBalance(accountNumber);
    }

    @PutMapping("/withdraw/{accountNumber}/{balance}")
    public String withdrawBalance(@PathVariable("accountNumber") String accountNumber,
                                  @PathVariable("balance") double balance){
        return accountService.withdrawBalance(accountNumber,balance);
    }

    @PutMapping("/recharge/{accountNumber}/{balance}")
    public String rechargeBalance(@PathVariable("accountNumber") String accountNumber,@PathVariable("balance") double balance){
        return accountService.rechargeBalance(accountNumber, balance);
    }

}