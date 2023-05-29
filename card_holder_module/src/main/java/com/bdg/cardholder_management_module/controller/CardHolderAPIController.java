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

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(
    		description = "Creating new card holder")
    @PostMapping(value = "/new")
    public boolean addCardHolder(
            @Valid @RequestBody CardHolderCreatingRequest cardHolderCreatingRequest
    ) {
        return cardHolderService.saveCardHolder(
                new PersonalInfoModel(cardHolderCreatingRequest.personalInfoCreatingRequest()),
                new PassportModel(cardHolderCreatingRequest.passportCreatingRequest())
        );
    }


    @Operation(
    		description = "Activate Card Holder")
    @PutMapping(value = "/activate/{serialNo}")
    public boolean activateCardHolder(
            @NotNull(message = "Passed null value as 'serialNumber': ")
            @NotBlank(message = "Passed blank value as 'serialNumber': ")
            @NotEmpty(message = "Passed empty value as 'serialNumber': ")
            @Pattern(
                    regexp = "[A-Z]{2}\\d{7}",
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("serialNo") String serialNumber
    ) {
        return cardHolderService.activateCardHolder(serialNumber);
    }


    @Operation(
    		description = "Updating personal info by Passport number")
    @PatchMapping(value = "/update/personalInfo/{serialNo}")
    public boolean updatePersonalInfoByPassportNo(
            @NotNull(message = "Passed null value as 'serialNumber': ")
            @NotBlank(message = "Passed blank value as 'serialNumber': ")
            @NotEmpty(message = "Passed empty value as 'serialNumber': ")
            @Pattern(
                    regexp = "[A-Z]{2}\\d{7}",
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("serialNo") String serialNumber,
            @Valid @RequestBody PersonalInfoUpdateRequest personalInfoUpdateRequest
    ) {
        return cardHolderService.updatePersonalInfoBySerialNumber(
                serialNumber,
                new PersonalInfoModel(personalInfoUpdateRequest)
        );
    }


    @Operation(
    		description = "Update passport by passport number")
    @PatchMapping(value = "/update/passportInfo/{serialNo}")
    public boolean updatePassportByPassportNo(
            @NotNull(message = "Passed null value as 'serialNumber': ")
            @NotBlank(message = "Passed blank value as 'serialNumber': ")
            @NotEmpty(message = "Passed empty value as 'serialNumber': ")
            @Pattern(
                    regexp = "[A-Z]{2}\\d{7}",
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("serialNo") String serialNumber,
            @Valid @RequestBody PassportUpdateRequest passportUpdateRequest
    ) {
        return cardHolderService.updatePassportBySerialNumber(
                serialNumber,
                new PassportModel(passportUpdateRequest)
        );
    }


    @Operation(
    		description = "Deletes passport by passport number")
    @DeleteMapping(value = "/delete/{serialNo}")
    public boolean deleteByPassportNo(
            @NotNull(message = "Passed null value as 'serialNumber': ")
            @NotBlank(message = "Passed blank value as 'serialNumber': ")
            @NotEmpty(message = "Passed empty value as 'serialNumber': ")
            @Pattern(
                    regexp = "[A-Z]{2}\\d{7}",
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("serialNo") String serialNumber
    ) {
        return cardHolderService.deleteCardHolderBySerialNumber(serialNumber);
    }


    @Operation(
    		description = "Add address on card holder")
    @PutMapping(value = "/update/addAddress/{serialNo}")
    public boolean addAddressOnCardHolder(
            @NotNull(message = "Passed null value as 'serialNumber': ")
            @NotBlank(message = "Passed blank value as 'serialNumber': ")
            @NotEmpty(message = "Passed empty value as 'serialNumber': ")
            @Pattern(
                    regexp = "[A-Z]{2}\\d{7}",
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("serialNo") String serialNumber,
            @Valid @RequestBody AddressCreatingRequest addressCreatingRequest
    ) {
        return cardHolderService.addAddressOnCardHolder(
                serialNumber,
                new AddressModel(addressCreatingRequest));
    }


    @Operation(
    		description = "Deletes address from card holder")
    @DeleteMapping(value = "/update/deleteAddress/{serialNo}")
    public boolean deleteAddressFromCardHolder(
            @NotNull(message = "Passed null value as 'serialNumber': ")
            @NotBlank(message = "Passed blank value as 'serialNumber': ")
            @NotEmpty(message = "Passed empty value as 'serialNumber': ")
            @Pattern(
                    regexp = "[A-Z]{2}\\d{7}",
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("serialNo") String serialNumber,
            @Valid @RequestBody AddressCreatingRequest addressToDelete
    ) {
        return cardHolderService.deleteAddressFromCardHolder(
                serialNumber,
                new AddressModel(addressToDelete));
    }


    @Operation(
    		description = "Find card holder by Email")
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


    @Operation(
    		description = "find Card Holder by phone")
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


    @Operation(
    		description = "Find Card Holder by serial number")
    @GetMapping(value = "/getPassNo/{serialNo}")
    public CardHolderResponse findByPassportNo(
            @NotNull(message = "Passed null value as 'serialNumber': ")
            @NotBlank(message = "Passed blank value as 'serialNumber': ")
            @NotEmpty(message = "Passed empty value as 'serialNumber': ")
            @Pattern(
                    regexp = "[A-Z]{2}\\d{7}",
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("serialNo") String serialNumber
    ) {
        return CardHolderResponse.getFromModel(cardHolderService.findCardHolderBySerialNumber(serialNumber));
    }


    @Operation(
    		description = "Get all users by Full Name")
    @GetMapping(value = "/getAllByFullName")
    public List<CardHolderResponse> findAllByFullName(
            @Valid @RequestBody FullNameForSearchRequest fullNameForSearchRequest
    ) {
        return cardHolderService.
                findCardHoldersByFullName(
                        fullNameForSearchRequest.firstName(),
                        fullNameForSearchRequest.lastName())
                .stream()
                .map(CardHolderResponse::getFromModel)
                .toList();
    }


    //TODO
    public List<CardHolderResponse> findAllByAddress(AddressForSearchRequest addressForSearchRequest) {
        return null;
    }
}