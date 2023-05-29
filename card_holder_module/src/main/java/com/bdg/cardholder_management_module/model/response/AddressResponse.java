package com.bdg.cardholder_management_module.model.response;

import com.bdg.cardholder_management_module.model.dto.AddressModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "address")
public record AddressResponse(
        @JsonProperty("street")
        String street,

        @JsonProperty("city")
        String city,

        @JsonProperty("country")
        String country
) {
    public static AddressResponse getFromModel(AddressModel addressModel) {
        return new AddressResponse(
                addressModel.getStreet(),
                addressModel.getCity(),
                addressModel.getCountry()
        );
    }
}