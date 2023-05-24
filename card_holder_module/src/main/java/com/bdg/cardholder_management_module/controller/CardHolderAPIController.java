package com.bdg.cardholder_management_module.controller;

import com.bdg.cardholder_management_module.model.AddressModel;
import com.bdg.cardholder_management_module.model.CardHolderModel;
import com.bdg.cardholder_management_module.model.PassportModel;
import com.bdg.cardholder_management_module.request.*;
import com.bdg.cardholder_management_module.response.CardHolderResponse;
import com.bdg.cardholder_management_module.service.CardHolderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return cardHolderService.updatePersonalInfoByPassportNumber(passportNo, new CardHolderModel(cardHolderRequest));
    }


    public boolean updatePassportInfoByPassNo() {
        return false;
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
        return cardHolderService.deleteByPassportNumber(passportNo);
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
    ) {
        return cardHolderService.addAddress(passportNo, new AddressModel(addressRequest));
    }


    public boolean deleteAddressFromCardHolder() {
        return false;
    }


    public CardHolderResponse findByEmail() {
        return null;
    }


    public CardHolderResponse findByPhone() {
        return null;
    }


    public CardHolderResponse findByPassportNo() {
        return null;
    }


    public List<CardHolderResponse> findAllByFullName(FullNameForSearchRequest fullNameForSearchRequest) {
        return null;
    }


    public List<CardHolderResponse> findAllByAddress(AddressForSearchRequest addressForSearchRequest) {
        return null;
    }
}
