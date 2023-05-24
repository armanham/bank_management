package com.bdg.cardholder_management_module.service;

import com.bdg.cardholder_management_module.model.AddressModel;
import com.bdg.cardholder_management_module.model.CardHolderModel;
import com.bdg.cardholder_management_module.model.PassportModel;

public interface CardHolderService {

    boolean save(CardHolderModel cardHolderModel, PassportModel passportModel);

    boolean updatePersonalInfoByPassportNumber(String passportNo, CardHolderModel cardHolderModel);

    boolean deleteByPassportNumber(String passportNo);

    boolean addAddress(String passportNo, AddressModel addressModel);
}
