package com.example.controller;

import com.example.entity.CardEntity;
import com.example.model.CardModel;
import com.example.services.CardService;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @GetMapping("/get/{cardNumber}")
    public Optional<CardEntity> getCard(@PathVariable("cardNumber")String cardNumber){
       return cardService.getCard(cardNumber);
    }


    @GetMapping("/getAll")
    public List<CardEntity> getAll(){
        return cardService.getAllCard();
    }

    @PostMapping("/new")
    public void createCard(@RequestBody CardEntity cardEntity) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        CardModel cardModel = new CardModel();
        cardModel.setUserId(cardEntity.getUserId());
        cardModel.setType(cardEntity.getType());
        cardModel.setCurrency(cardEntity.getCurrency());
        cardService.createCard(cardModel);
    }

    @DeleteMapping("/delete/{cardNumber}")
    public void updateIsDeleted(@PathVariable("cardNumber") String cardNumber){
        cardService.updateIsDeleted(cardNumber);
        cardService.deactivate(cardNumber);
    }

   @PutMapping("/activate/{cardNumber}")
    public String activate(@PathVariable("cardNumber") String cardNumber){
       return cardService.activate(cardNumber);
    }

    @PutMapping("/block/{cardNumber}")
    public String block(@PathVariable("cardNumber") String cardNumber){
      return  cardService.block(cardNumber);
    }

    @PutMapping("/deactivate/{cardNumber}")
    public void deactivate(@PathVariable("cardNumber") String cardNumber){
        cardService.deactivate(cardNumber);
    }

    @PutMapping("/change/{cardNumber}/{newPin}")
    public String changePin(@PathVariable String cardNumber,
                            @PathVariable String newPin) {
     return cardService.changePin(cardNumber, newPin);
    }



    @PutMapping("/recharge/{cardNumber}/{balance}")
    public String rechargeBalance(@PathVariable("cardNumber") String cardNumber,@PathVariable("balance") double balance){
        return cardService.rechargeBalance(cardNumber, balance);
    }

    @GetMapping("/check/balance/{cardNumber}")
    public String checkBalance(@PathVariable("cardNumber") String cardNumber){
       return cardService.checkBalance(cardNumber);
    }

    @PutMapping("/withdraw/{cardNumber}/{balance}")
    public String withdrawBalance(@PathVariable("cardNumber") String cardNumber,
                                  @PathVariable("balance") double balance){
       return cardService.withdrawBalance(cardNumber,balance);
    }


/**
    @GetMapping("/update/{cardNumber}/{status}")
    public String updateStatus(@PathVariable("cardNumber") String cardNumber, @PathVariable("status") String status) {
       return cardService.updateStatus(cardNumber,status);
    }
    */
}
