package com.bdg.cardholder_management_module.controller;

public class Endpoints {

    public static final String API_CARD_HOLDER = "/api/cardholder";
    public static final String CARDHOLDER_NEW = "/new";
    public static final String CARDHOLDER_ACTIVATE = "/activate/{serialNo}";
    public static final String CARDHOLDER_UPDATE_PERSONAL_INFO_BY_PASSPORT_NO = "/update/personalInfo/{serialNo}";
    public static final String CARDHOLDER_UPDATE_PASSPORT_BY_PASSPORT_NO = "/update/passportInfo/{serialNo}";
    public static final String DELETE_BY_PASSPORT_NO = "/delete/{serialNo}";
    public static final String ADD_ADDRESS_ON_CARD_HOLDER = "/update/addAddress/{serialNo})";
    public static final String DELETE_ADDRESS_FROM_CARD_HOLDER = "/update/deleteAddress/{serialNo}";
    public static final String FIND_BY_PHONE = "/getByPhone/{phone}";
    public static final String FIND_BY_EMAIL = "/getByEmail/{email}";
    public static final String FIND_BY_PASS_NO = "/getPassNo/{serialNo}";
    public static final String FIND_ALL_BY_FULL_NAME = "/getAllByFullName";
    public static final String FIND_ACTIVE_CARD_HOLDERS = "/getActiveCardHolders";
    public static final String FIND_DELETED_CARD_HOLDERS = "/getDeletedCardHolders";
}
