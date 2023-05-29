package com.bdg.cardholder_management_module.controller;

import com.bdg.cardholder_management_module.check.validator.annotation.NotNullEmptyBlankString;
import com.bdg.cardholder_management_module.model.dto.AddressModel;
import com.bdg.cardholder_management_module.model.dto.PassportModel;
import com.bdg.cardholder_management_module.model.dto.PersonalInfoModel;
import com.bdg.cardholder_management_module.model.request.create.AddressCreatingRequest;
import com.bdg.cardholder_management_module.model.request.create.CardHolderCreatingRequest;
import com.bdg.cardholder_management_module.model.request.search.AddressForSearchRequest;
import com.bdg.cardholder_management_module.model.request.search.FullNameForSearchRequest;
import com.bdg.cardholder_management_module.model.request.update.PassportUpdateRequest;
import com.bdg.cardholder_management_module.model.request.update.PersonalInfoUpdateRequest;
import com.bdg.cardholder_management_module.model.response.CardHolderResponse;
import com.bdg.cardholder_management_module.service.CardHolderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bdg.cardholder_management_module.controller.Endpoints.*;
import static com.bdg.cardholder_management_module.check.pattern.Pattern.*;

@RestController
@RequestMapping(value = API_CARD_HOLDER)
@Valid
@Validated
public class CardHolderAPIController {

    private final CardHolderService cardHolderService;

    @Autowired
    public CardHolderAPIController(CardHolderService cardHolderService) {
        this.cardHolderService = cardHolderService;
    }


    @PostMapping(value = CARDHOLDER_NEW)
    public boolean addCardHolder(
            @Valid @RequestBody CardHolderCreatingRequest cardHolderCreatingRequest
    ) {
        return cardHolderService.saveCardHolder(
                new PersonalInfoModel(cardHolderCreatingRequest.personalInfoCreatingRequest()),
                new PassportModel(cardHolderCreatingRequest.passportCreatingRequest())
        );
    }


    @PutMapping(value = CARDHOLDER_ACTIVATE)
    public boolean activateCardHolder(
            @NotNullEmptyBlankString
            @Pattern(
                    regexp = PASSPORT_NO_PATTERN,
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("serialNo") String serialNumber
    ) {
        return cardHolderService.activateCardHolder(serialNumber);
    }


    @PatchMapping(value = CARDHOLDER_UPDATE_PERSONAL_INFO_BY_PASSPORT_NO)
    public boolean updatePersonalInfoByPassportNo(
            @NotNullEmptyBlankString
            @Pattern(
                    regexp = PASSPORT_NO_PATTERN,
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


    @PatchMapping(value = CARDHOLDER_UPDATE_PASSPORT_BY_PASSPORT_NO)
    public boolean updatePassportByPassportNo(
            @NotNullEmptyBlankString
            @Pattern(
                    regexp = PASSPORT_NO_PATTERN,
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


    @DeleteMapping(value = DELETE_BY_PASSPORT_NO)
    public boolean deleteByPassportNo(
            @NotNullEmptyBlankString
            @Pattern(
                    regexp = PASSPORT_NO_PATTERN,
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("serialNo") String serialNumber
    ) {
        return cardHolderService.deleteCardHolderBySerialNumber(serialNumber);
    }


    @PutMapping(value = ADD_ADDRESS_ON_CARD_HOLDER)
    public boolean addAddressOnCardHolder(
            @NotNullEmptyBlankString
            @Pattern(
                    regexp = PASSPORT_NO_PATTERN,
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("serialNo") String serialNumber,
            @Valid @RequestBody AddressCreatingRequest addressCreatingRequest
    ) {
        return cardHolderService.addAddressOnCardHolder(
                serialNumber,
                new AddressModel(addressCreatingRequest));
    }


    @DeleteMapping(value = DELETE_ADDRESS_FROM_CARD_HOLDER)
    public boolean deleteAddressFromCardHolder(
            @NotNullEmptyBlankString
            @Pattern(
                    regexp = PASSPORT_NO_PATTERN,
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("serialNo") String serialNumber,
            @Valid @RequestBody AddressCreatingRequest addressToDelete
    ) {
        return cardHolderService.deleteAddressFromCardHolder(
                serialNumber,
                new AddressModel(addressToDelete));
    }


    @GetMapping(value = FIND_BY_EMAIL)
    public CardHolderResponse findCardHolderByEmail(
            @NotNullEmptyBlankString
            @Email
            @PathVariable("email") String email
    ) {
        return CardHolderResponse.getFromModel(cardHolderService.findCardHolderByEmail(email));
    }


    @GetMapping(value = FIND_BY_PHONE)
    public CardHolderResponse findCardHolderByPhone(
            @NotNullEmptyBlankString
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


    @GetMapping(value = FIND_BY_PASS_NO)
    public CardHolderResponse findByPassportNo(
            @NotNullEmptyBlankString
            @Pattern(
                    regexp = PASSPORT_NO_PATTERN,
                    message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
            )
            @PathVariable("serialNo") String serialNumber
    ) {
        return CardHolderResponse.getFromModel(cardHolderService.findCardHolderBySerialNumber(serialNumber));
    }


    @GetMapping(value = FIND_ALL_BY_FULL_NAME)
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


    @GetMapping(value = FIND_ACTIVE_CARD_HOLDERS)
    public List<CardHolderResponse> findActiveCardHolders() {
        return cardHolderService
                .findActiveCardHolders()
                .stream()
                .map(CardHolderResponse::getFromModel)
                .toList();
    }


    @GetMapping(value = FIND_DELETED_CARD_HOLDERS)
    public List<CardHolderResponse> findDeletedCardHolders() {
        return cardHolderService
                .findDeletedCardHolders()
                .stream()
                .map(CardHolderResponse::getFromModel)
                .toList();
    }


    //TODO
    public List<CardHolderResponse> findAllByAddress(AddressForSearchRequest addressForSearchRequest) {
        return null;
    }
}