package com.bdg.cardholder_management_module.model.dto;

import com.bdg.cardholder_management_module.model.entity.PassportEntity;
import com.bdg.cardholder_management_module.model.request.create.PassportCreatingRequest;
import com.bdg.cardholder_management_module.model.request.update.PassportUpdateRequest;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassportModel that = (PassportModel) o;
        return Objects.equals(serialNumber, that.serialNumber) && Objects.equals(nationality, that.nationality) && Objects.equals(givenDate, that.givenDate) && Objects.equals(expireDate, that.expireDate) && Objects.equals(givenBy, that.givenBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, nationality, givenDate, expireDate, givenBy);
    }
}