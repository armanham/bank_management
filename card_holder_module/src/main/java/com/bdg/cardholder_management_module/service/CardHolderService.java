package com.bdg.cardholder_management_module.service;

import com.bdg.cardholder_management_module.model.AddressModel;
import com.bdg.cardholder_management_module.model.CardHolderModel;
import com.bdg.cardholder_management_module.model.PassportModel;
import com.bdg.cardholder_management_module.model.PersonalInfoModel;

import java.util.List;

public interface CardHolderService {

    boolean saveCardHolder(PersonalInfoModel personalInfoModel, PassportModel passportModel);

    boolean activateCardHolder(String serialNumber);

    boolean updatePersonalInfoBySerialNumber(String serialNumber, PersonalInfoModel personalInfoModel);

    boolean updatePassportBySerialNumber(String serialNumber, PassportModel passportModel);

    boolean deleteCardHolderBySerialNumber(String serialNumber);

    boolean addAddressOnCardHolder(String serialNumber, AddressModel addressModel);

    boolean deleteAddressFromCardHolder(String serialNumber, AddressModel addressModel);

    CardHolderModel findCardHolderBySerialNumber(String serialNumber);

    CardHolderModel findCardHolderByPhone(String phone);

    CardHolderModel findCardHolderByEmail(String email);

    List<CardHolderModel> findCardHoldersByFullName(String firstName, String lastName);
}