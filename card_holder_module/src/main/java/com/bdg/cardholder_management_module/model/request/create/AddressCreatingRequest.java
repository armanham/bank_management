package com.bdg.cardholder_management_module.model.request.create;

import com.bdg.cardholder_management_module.check.validator.annotation.NotNullEmptyBlankString;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;

public record AddressCreatingRequest(

        @NotNullEmptyBlankString
        @Pattern(regexp = "^[A-Za-z0-9\\s,.-]*$")
        @JsonProperty("street")
        String street,

        @NotNullEmptyBlankString
        @Pattern(regexp = "^[A-Za-z\\s.-]*$")
        @JsonProperty("city")
        String city,

        @NotNullEmptyBlankString
        @Pattern(regexp = "^[A-Za-z\\s',.-]*$")
        @JsonProperty("country")
        String country
) {
}