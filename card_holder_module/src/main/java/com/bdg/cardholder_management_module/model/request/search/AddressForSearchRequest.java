package com.bdg.cardholder_management_module.model.request.search;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressForSearchRequest(
        @JsonProperty("street")
        String street,

        @JsonProperty("city")
        String city,

        @JsonProperty("country")
        String country
) {
}