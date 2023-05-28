package com.bdg.cardholder_management_module.model;

import com.bdg.cardholder_management_module.entity.PassportEntity;
import com.bdg.cardholder_management_module.request.create.PassportCreatingRequest;
import com.bdg.cardholder_management_module.request.update.PassportUpdateRequest;

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

    public PassportModel(PassportCreatingRequest passportCreatingRequest) {
        this.serialNumber = passportCreatingRequest.serialNumber();
        this.nationality = passportCreatingRequest.nationality();
        this.givenDate = passportCreatingRequest.givenDate();
        this.expireDate = passportCreatingRequest.expireDate();
        this.givenBy = passportCreatingRequest.givenBy();
    }

    public PassportModel getFromRequest(PassportCreatingRequest passportCreatingRequest) {
        this.serialNumber = passportCreatingRequest.serialNumber();
        this.nationality = passportCreatingRequest.nationality();
        this.givenDate = passportCreatingRequest.givenDate();
        this.expireDate = passportCreatingRequest.expireDate();
        this.givenBy = passportCreatingRequest.givenBy();
        return this;
    }

    public PassportModel(PassportEntity passportEntity) {
        this.serialNumber = passportEntity.getSerialNumber();
        this.nationality = passportEntity.getNationality();
        this.givenDate = passportEntity.getGivenDate().toString();
        this.expireDate = passportEntity.getExpireDate().toString();
        this.givenBy = passportEntity.getGivenBy();
    }

    public PassportModel(PassportUpdateRequest passportUpdateRequest){
        this.nationality = passportUpdateRequest.nationality();
        this.givenDate = passportUpdateRequest.givenDate();
        this.expireDate = passportUpdateRequest.expireDate();
        this.givenBy = passportUpdateRequest.givenBy();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(String givenDate) {
        this.givenDate = givenDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getGivenBy() {
        return givenBy;
    }

    public void setGivenBy(String givenBy) {
        this.givenBy = givenBy;
    }
}