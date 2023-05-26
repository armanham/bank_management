package com.bdg.cardholder_management_module.service;

import com.bdg.cardholder_management_module.model.AddressModel;
import com.bdg.cardholder_management_module.model.CardHolderModel;
import com.bdg.cardholder_management_module.model.PassportModel;
import com.bdg.cardholder_management_module.model.PersonalInfoModel;

public interface CardHolderService {

    boolean saveCardHolder(PersonalInfoModel personalInfoModel, PassportModel passportModel);

    boolean activateCardHolder(String passportNo);

    boolean updatePersonalInfoByPassportNumber(String passportNo, PersonalInfoModel personalInfoModel);

    boolean updatePassportByPassportNumber(String passportNo, PassportModel passportModel);

    boolean deleteCardHolderByPassportNumber(String passportNo);

    boolean addAddressOnCardHolder(String passportNo, AddressModel addressModel);

    boolean deleteAddressFromCardHolder(String passportNo, AddressModel addressModel);

    CardHolderModel findCardHolderByPassportNo(String passportNo);

    CardHolderModel findCardHolderByPhone(String phone);

    CardHolderModel findCardHolderByEmail(String email);
}