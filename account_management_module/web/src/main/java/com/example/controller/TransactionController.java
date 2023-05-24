package com.example.controller;

import com.example.entity.CardEntity;
import com.example.entity.TransactionEntity;
import com.example.model.TransactionModel;
import com.example.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/get/{tNumber}")
    public Optional<TransactionEntity> get(@PathVariable("tNumber") String transactionNumber){
     return transactionService.get(transactionNumber);
    }


@GetMapping("/getAll")
    public List<TransactionEntity> getAll(){
        return transactionService.getAll();
    }

    @PostMapping("/new")
    public String transactional(@RequestBody TransactionEntity te){
      return transactionService.transaction(te);
    }


}
