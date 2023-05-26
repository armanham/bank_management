package com.bdg.cardholder_management_module.request.search;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FullNameForSearchRequest(

        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName
) {
}