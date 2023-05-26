package com.bdg.cardholder_management_module.model;

import com.bdg.cardholder_management_module.entity.CardHolderEntity;
import com.bdg.cardholder_management_module.entity.CardHolderType;
import com.bdg.cardholder_management_module.request.create.PersonalInfoCreatingRequest;
import com.bdg.cardholder_management_module.request.update.PersonalInfoUpdateRequest;

public final class PersonalInfoModel {

    private String firstName;
    private String lastName;
    private String dob;
    private String phone;
    private String email;
    private CardHolderType cardHolderType;

    public PersonalInfoModel(
            String firstName,
            String lastName,
            String dob,
            String phone,
            String email,
            CardHolderType cardHolderType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.cardHolderType = cardHolderType;
    }

    public PersonalInfoModel getFromRequest(PersonalInfoCreatingRequest personalInfoCreatingRequest) {
        this.firstName = personalInfoCreatingRequest.firstName();
        this.lastName = personalInfoCreatingRequest.lastName();
        this.dob = personalInfoCreatingRequest.dob();
        this.phone = personalInfoCreatingRequest.phone();
        this.email = personalInfoCreatingRequest.email();
        this.cardHolderType = CardHolderType.valueOf(personalInfoCreatingRequest.cardHolderType());
        return this;
    }

    public PersonalInfoModel(PersonalInfoCreatingRequest personalInfoCreatingRequest) {
        this.firstName = personalInfoCreatingRequest.firstName();
        this.lastName = personalInfoCreatingRequest.lastName();
        this.dob = personalInfoCreatingRequest.dob();
        this.phone = personalInfoCreatingRequest.phone();
        this.email = personalInfoCreatingRequest.email();
        this.cardHolderType = CardHolderType.valueOf(personalInfoCreatingRequest.cardHolderType());
    }


    public PersonalInfoModel(PersonalInfoUpdateRequest personalInfoUpdateRequest) {
        this.firstName = personalInfoUpdateRequest.firstName();
        this.lastName = personalInfoUpdateRequest.lastName();
        this.dob = personalInfoUpdateRequest.dob();
        this.phone = personalInfoUpdateRequest.phone();
        this.email = personalInfoUpdateRequest.email();
        if (personalInfoUpdateRequest.cardHolderType() != null) {
            this.cardHolderType = CardHolderType.valueOf(personalInfoUpdateRequest.cardHolderType());
        }
    }

    public PersonalInfoModel(CardHolderEntity cardHolderEntity) {
        this.firstName = cardHolderEntity.getFirstName();
        this.lastName = cardHolderEntity.getLastName();
        this.dob = cardHolderEntity.getDob().toString();
        this.phone = cardHolderEntity.getPhone();
        this.email = cardHolderEntity.getEmail();
        this.cardHolderType = cardHolderEntity.getCardHolderType();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CardHolderType getCardHolderType() {
        return cardHolderType;
    }

    public void setCardHolderType(CardHolderType cardHolderType) {
        this.cardHolderType = cardHolderType;
    }
}