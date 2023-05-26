package com.bdg.cardholder_management_module.controller;

import com.bdg.cardholder_management_module.model.AddressModel;
import com.bdg.cardholder_management_module.model.PassportModel;
import com.bdg.cardholder_management_module.model.PersonalInfoModel;
import com.bdg.cardholder_management_module.request.create.AddressCreatingRequest;
import com.bdg.cardholder_management_module.request.create.CardHolderCreatingRequest;
import com.bdg.cardholder_management_module.request.search.AddressForSearchRequest;
import com.bdg.cardholder_management_module.request.search.FullNameForSearchRequest;
import com.bdg.cardholder_management_module.request.update.PassportUpdateRequest;
import com.bdg.cardholder_management_module.request.update.PersonalInfoUpdateRequest;
import com.bdg.cardholder_management_module.response.CardHolderResponse;
import com.bdg.cardholder_management_module.service.CardHolderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    public boolean addCardHolder(
            @Valid @RequestBody CardHolderCreatingRequest cardHolderCreatingRequest
    ) {
        return cardHolderService.saveCardHolder(
                new PersonalInfoModel(cardHolderCreatingRequest.personalInfoCreatingRequest()),
                new PassportModel(cardHolderCreatingRequest.passportCreatingRequest())
        );
    }


    @PatchMapping(value = "/update/personalInfo/{passNo}")
    public boolean updatePersonalInfoByPassportNo(
            @NotNull(message = "Passed null value as 'passportNo': ")
            @NotBlank(message = "Passed blank value as 'passportNo': ")
            @NotEmpty(message = "Passed empty value as 'passportNo': ")
            @Pattern(
                    regexp = "[A-Z]{2}\\d{7}",
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("passNo") String passportNo,
            @Valid @RequestBody PersonalInfoUpdateRequest personalInfoUpdateRequest
    ) {
        return cardHolderService.updatePersonalInfoByPassportNumber(
                passportNo,
                new PersonalInfoModel(personalInfoUpdateRequest)
        );
    }


    @PatchMapping(value = "/update/personalInfo/{passNo}")
    public boolean updatePassportByPassportNo(
            @NotNull(message = "Passed null value as 'passportNo': ")
            @NotBlank(message = "Passed blank value as 'passportNo': ")
            @NotEmpty(message = "Passed empty value as 'passportNo': ")
            @Pattern(
                    regexp = "[A-Z]{2}\\d{7}",
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("passNo") String passportNo,
            @Valid @RequestBody PassportUpdateRequest passportUpdateRequest
    ) {
        return cardHolderService.updatePassportByPassportNumber(
                passportNo,
                new PassportModel(passportUpdateRequest)
        );
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
        return cardHolderService.deleteCardHolderByPassportNumber(passportNo);
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
            @Valid @RequestBody AddressCreatingRequest addressCreatingRequest
    ) {
        return cardHolderService.addAddressOnCardHolder(
                passportNo,
                new AddressModel(addressCreatingRequest));
    }


    @DeleteMapping(value = "/update/deleteAddress/{passNo}")
    public boolean deleteAddressFromCardHolder(
            @NotNull(message = "Passed null value as 'passportNo': ")
            @NotBlank(message = "Passed blank value as 'passportNo': ")
            @NotEmpty(message = "Passed empty value as 'passportNo': ")
            @Pattern(
                    regexp = "[A-Z]{2}\\d{7}",
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("passNo") String passportNo,
            @Valid @RequestBody AddressCreatingRequest addressToDelete
    ) {
        return cardHolderService.deleteAddressFromCardHolder(
                passportNo,
                new AddressModel(addressToDelete));
    }


    @GetMapping(value = "/getByEmail/{email}")
    public CardHolderResponse findCardHolderByEmail(
            @NotNull(message = "Passed null value as 'email': ")
            @NotBlank(message = "Passed blank value as 'email': ")
            @NotEmpty(message = "Passed empty value as 'email': ")
            @Email
            @PathVariable("email") String email
    ) {
        return CardHolderResponse.getFromModel(cardHolderService.findCardHolderByEmail(email));
    }


    @GetMapping(value = "/getByPhone/{phone}")
    public CardHolderResponse findCardHolderByPhone(
            @NotNull(message = "Passed null value as 'phone': ")
            @NotBlank(message = "Passed blank value as 'phone': ")
            @NotEmpty(message = "Passed empty value as 'phone': ")
            @Pattern(
                    regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$",
                    message = "The phone number must be like these: \n" +
                            "123-456-7890\n" +
                            "(123) 456-7890\n" +
                            "123 456 7890\n" +
                            "123.456.7890\n" +
                            "+91 (123) 456-7890"
            )
            @PathVariable("phone") String phone
    ) {
        return CardHolderResponse.getFromModel(cardHolderService.findCardHolderByPhone(phone));
    }


    @GetMapping(value = "/getPassNo/{passNo}")
    public CardHolderResponse findByPassportNo(
            @NotNull(message = "Passed null value as 'passportNo': ")
            @NotBlank(message = "Passed blank value as 'passportNo': ")
            @NotEmpty(message = "Passed empty value as 'passportNo': ")
            @Pattern(
                    regexp = "[A-Z]{2}\\d{7}",
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("passNo") String passportNo
    ) {
        return CardHolderResponse.getFromModel(cardHolderService.findCardHolderByPassportNo(passportNo));
    }


    //TODO
    public List<CardHolderResponse> findAllByFullName(FullNameForSearchRequest fullNameForSearchRequest) {
        return null;
    }


    //TODO
    public List<CardHolderResponse> findAllByAddress(AddressForSearchRequest addressForSearchRequest) {
        return null;
    }
}