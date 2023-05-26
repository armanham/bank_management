package com.bdg.cardholder_management_module.request.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record FullNameForSearchRequest(

        @NotNull
        @JsonProperty("first_name")
        String firstName,

        @NotNull
        @JsonProperty("last_name")
        String lastName
) {
}