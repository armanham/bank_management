package com.bdg.cardholder_management_module.model.dto;

import com.bdg.cardholder_management_module.model.entity.AddressEntity;
import com.bdg.cardholder_management_module.model.request.create.AddressCreatingRequest;

import java.util.Objects;

public final class AddressModel {

    private String street;
    private String city;
    private String country;

    public AddressModel() {
    }

    public AddressModel(
            String street,
            String city,
            String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public AddressModel(AddressCreatingRequest addressCreatingRequest) {
        this.street = addressCreatingRequest.street();
        this.city = addressCreatingRequest.city();
        this.country = addressCreatingRequest.country();
    }

    public AddressModel(AddressEntity addressEntity) {
        this.street = addressEntity.getStreet();
        this.city = addressEntity.getCity();
        this.country = addressEntity.getCountry();
    }

    public AddressModel getFromRequest(AddressCreatingRequest addressCreatingRequest) {
        this.street = addressCreatingRequest.street();
        this.city = addressCreatingRequest.city();
        this.country = addressCreatingRequest.country();
        return this;
    }

    public AddressModel getFromEntity(AddressEntity addressEntity) {
        this.street = addressEntity.getStreet();
        this.city = addressEntity.getCity();
        this.country = addressEntity.getCountry();
        return this;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressModel that = (AddressModel) o;
        return Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, country);
    }
}