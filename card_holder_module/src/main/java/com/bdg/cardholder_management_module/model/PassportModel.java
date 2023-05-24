package com.bdg.cardholder_management_module.model;

import com.bdg.cardholder_management_module.request.PassportRequest;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public final class PassportModel {

    private String serialNumber;
    private String nationality;
    private String givenDate;
    private String expireDate;
    private String givenBy;

    public PassportModel() {
    }

    public PassportModel(
            String serialNumber,
            String nationality,
            String givenDate,
            String expireDate,
            String givenBy) {
        this.serialNumber = serialNumber;
        this.nationality = nationality;
        this.givenDate = givenDate;
        this.expireDate = expireDate;
        this.givenBy = givenBy;
    }

    public PassportModel(PassportRequest passportRequest){
        this.serialNumber = passportRequest.serialNumber();
        this.nationality = passportRequest.nationality();
        this.givenDate = passportRequest.givenDate();
        this.expireDate = passportRequest.expireDate();
        this.givenBy = passportRequest.givenBy();
    }
    public PassportModel getFromRequest(PassportRequest passportRequest){
        this.serialNumber = passportRequest.serialNumber();
        this.nationality = passportRequest.nationality();
        this.givenDate = passportRequest.givenDate();
        this.expireDate = passportRequest.expireDate();
        this.givenBy = passportRequest.givenBy();
        return this;
    }


}