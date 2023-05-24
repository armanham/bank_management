package com.bdg.cardholder_management_module.service;

import com.bdg.cardholder_management_module.model.AddressModel;

public interface AddressService {

    boolean save(AddressModel addressModel);
    boolean update(AddressModel addressModel, long id);
    public boolean delete( long id);
}