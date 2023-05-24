package com.bdg.cardholder_management_module.controller;

import com.bdg.cardholder_management_module.model.AddressModel;
import com.bdg.cardholder_management_module.model.CardHolderModel;
import com.bdg.cardholder_management_module.model.PassportModel;
import com.bdg.cardholder_management_module.request.AddressRequest;
import com.bdg.cardholder_management_module.request.CardHolderPassportRequest;
import com.bdg.cardholder_management_module.request.CardHolderRequest;
import com.bdg.cardholder_management_module.service.CardHolderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/cardholder")
@Valid
@Validated
public class CardHolderAPIController {

    private final CardHolderService cardHolderService;

    @Autowired
    public CardHolderAPIController(CardHolderService cardHolderService) {
        this.cardHolderService = cardHolderService;
    }

    @PostMapping(value = "/new")
    public boolean add(
            @Valid @RequestBody CardHolderPassportRequest cardHolderPassportRequest
    ) {
        cardHolderService.save(
                new CardHolderModel(cardHolderPassportRequest.getCardHolderRequest()),
                new PassportModel(cardHolderPassportRequest.getPassportRequest())
        );
        return true;
    }


    @PutMapping(value = "/update/personalInfo/{passNo}")
    public boolean updatePersonalInfoByPassportNo(
            @NotNull(message = "Passed null value as 'passportNo': ")
            @NotBlank(message = "Passed blank value as 'passportNo': ")
            @NotEmpty(message = "Passed empty value as 'passportNo': ")
            @Pattern(
                    regexp = "[A-Z]{2}\\d{7}",
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("passNo") String passportNo,
            @RequestBody CardHolderRequest cardHolderRequest
    ) {
        return cardHolderService.updatePersonalInfo(passportNo, new CardHolderModel(cardHolderRequest));
    }


    @DeleteMapping(value = "/delete/{passNo}")
    public boolean deleteByPassportNo(
            @NotNull(message = "Passed null value as 'passportNo': ")
            @NotBlank(message = "Passed blank value as 'passportNo': ")
            @NotEmpty(message = "Passed empty value as 'passportNo': ")
            @Pattern(
                    regexp = "[A-Z]{2}\\d{7}",
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("passNo") String passportNo
    ) {
        return cardHolderService.delete(passportNo);
    }


    @PutMapping(value = "/update/addAddress/{passNo}")
    public boolean addAddressOnCardHolder(
            @NotNull(message = "Passed null value as 'passportNo': ")
            @NotBlank(message = "Passed blank value as 'passportNo': ")
            @NotEmpty(message = "Passed empty value as 'passportNo': ")
            @Pattern(
                    regexp = "[A-Z]{2}\\d{7}",
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("passNo") String passportNo,
            @RequestBody AddressRequest addressRequest
            ){
        return cardHolderService.addAddress(passportNo, new AddressModel(addressRequest));
    }
}