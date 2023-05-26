package com.bdg.cardholder_management_module.model;

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
}
