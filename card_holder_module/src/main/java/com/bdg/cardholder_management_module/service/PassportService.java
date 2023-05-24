package com.bdg.cardholder_management_module.service;

import com.bdg.cardholder_management_module.model.PassportModel;

public interface PassportService {
    boolean save(PassportModel passportModel);

    boolean update(PassportModel passportModel);

    public boolean delete(String serialNumber);
}
