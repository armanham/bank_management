package com.bdg.cardholder_management_module.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AddressRequest(

        @NotNull(message = "Passed null value as 'street': ")
        @NotBlank(message = "Passed blank value as 'street': ")
        @NotEmpty(message = "Passed empty value as 'street': ")
        @Pattern(regexp = "^[A-Za-z0-9\\s,.-]*$")
        String street,

        @NotNull(message = "Passed null value as 'city': ")
        @NotBlank(message = "Passed blank value as 'city': ")
        @NotEmpty(message = "Passed empty value as 'city': ")
        @Pattern(regexp = "^[A-Za-z\\s.-]*$")
        String city,

        @NotNull(message = "Passed null value as 'country': ")
        @NotBlank(message = "Passed blank value as 'country': ")
        @NotEmpty(message = "Passed empty value as 'country': ")
        @Pattern(regexp = "^[A-Za-z\\s',.-]*$")
        String country
) {
}