package com.bdg.cardholder_management_module.model.dto;

import java.util.Objects;
import java.util.Set;

public class CardHolderModel {

    private PersonalInfoModel personalInfoModel;
    private PassportModel passportModel;
    private Set<AddressModel> addressModelList;

    public CardHolderModel(
            PersonalInfoModel personalInfoModel,
            PassportModel passportModel,
            Set<AddressModel> addressModelList) {
        this.personalInfoModel = personalInfoModel;
        this.passportModel = passportModel;
        this.addressModelList = addressModelList;
    }

    public PersonalInfoModel getPersonalInfoModel() {
        return personalInfoModel;
    }

    public void setPersonalInfoModel(PersonalInfoModel personalInfoModel) {
        this.personalInfoModel = personalInfoModel;
    }

    public PassportModel getPassportModel() {
        return passportModel;
    }

    public void setPassportModel(PassportModel passportModel) {
        this.passportModel = passportModel;
    }

    public Set<AddressModel> getAddressModelList() {
        return addressModelList;
    }

    public void setAddressModelList(Set<AddressModel> addressModelList) {
        this.addressModelList = addressModelList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardHolderModel that = (CardHolderModel) o;
        return Objects.equals(personalInfoModel, that.personalInfoModel) && Objects.equals(passportModel, that.passportModel) && Objects.equals(addressModelList, that.addressModelList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalInfoModel, passportModel, addressModelList);
    }
}