package com.bdg.cardholder_management_module.request;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardHolderPassportRequest {

    @Valid
    CardHolderRequest cardHolderRequest;

    @Valid
    PassportRequest passportRequest;
}