package com.bdg.cardholder_management_module.model;

import com.bdg.cardholder_management_module.entity.AddressEntity;
import com.bdg.cardholder_management_module.request.AddressRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class AddressModel {

    private Long id;
    private String street;
    private String city;
    private String country;

    public AddressModel() {
    }

    public AddressModel(
            Long id,
            String street,
            String city,
            String country) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.country = country;
    }
    public AddressModel(AddressRequest addressRequest){
        this.street = addressRequest.street();
        this.city = addressRequest.city();
        this.country = addressRequest.country();
    }

    public AddressModel(AddressEntity addressEntity){
        this.street = addressEntity.getStreet();
        this.city = addressEntity.getCity();
        this.country = addressEntity.getCountry();
    }

    public AddressModel getFromRequest(AddressRequest addressRequest){
        this.street = addressRequest.street();
        this.city = addressRequest.city();
        this.country = addressRequest.country();
        return this;
    }

    public AddressModel getFromEntity(AddressEntity addressEntity){
        this.street = addressEntity.getStreet();
        this.city = addressEntity.getCity();
        this.country = addressEntity.getCountry();
        return this;
    }
}