package com.bdg.cardholder_management_module.model;

import com.bdg.cardholder_management_module.entity.CardHolderType;
import com.bdg.cardholder_management_module.request.CardHolderRequest;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Getter
@Setter
public final class CardHolderModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String dob;
    private String phone;
    private String email;
    private CardHolderType cardHolderType;

    public CardHolderModel(
            Long id,
            String firstName,
            String lastName,
            String dob,
            String phone,
            String email,
            CardHolderType cardHolderType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.cardHolderType = cardHolderType;
    }

    public CardHolderModel getFromRequest(CardHolderRequest cardHolderRequest){
        this.firstName = cardHolderRequest.firstName();
        this.lastName = cardHolderRequest.lastName();
        this.dob = cardHolderRequest.dob();
        this.phone = cardHolderRequest.phone();
        this.email = cardHolderRequest.email();
        this.cardHolderType = CardHolderType.valueOf(cardHolderRequest.cardHolderType());
        return this;
    }

    public CardHolderModel(CardHolderRequest cardHolderRequest) {
        this.firstName = cardHolderRequest.firstName();
        this.lastName = cardHolderRequest.lastName();
        this.dob = cardHolderRequest.dob();
        this.phone = cardHolderRequest.phone();
        this.email = cardHolderRequest.email();
        this.cardHolderType = CardHolderType.valueOf(cardHolderRequest.cardHolderType());
    }
}