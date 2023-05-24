package com.bdg.cardholder_management_module.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record PassportRequest(

        @NotNull(message = "Passed null value as 'serialNumber': ")
        @NotBlank(message = "Passed blank value as 'serialNumber': ")
        @NotEmpty(message = "Passed empty value as 'serialNumber': ")
        @Pattern(
                regexp = "[A-Z]{2}\\d{7}",
                message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
        )
        String serialNumber,

        @NotNull(message = "Passed null value as 'nationality': ")
        @NotBlank(message = "Passed blank value as 'nationality': ")
        @NotEmpty(message = "Passed empty value as 'nationality': ")
        @Length(
                min = 2,
                max = 32,
                message = "'nationality' length must be in range[2; 32]"
        )
        String nationality,

        @NotNull(message = "Passed null value as 'givenDate': ")
        @NotBlank(message = "Passed blank value as 'givenDate': ")
        @NotEmpty(message = "Passed empty value as 'givenDate': ")
        @Pattern(
                regexp = "^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|1\\d|2[0-8]|(0[1-9]|1[0-9]|2[0-9])(?=\\d))$",
                message = "Regex for 'givenDate': yyyy-mm-dd"
        )
        String givenDate,

        @NotNull(message = "Passed null value as 'expireDate': ")
        @NotBlank(message = "Passed blank value as 'expireDate': ")
        @NotEmpty(message = "Passed empty value as 'expireDate': ")
        @Pattern(
                regexp = "^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|1\\d|2[0-8]|(0[1-9]|1[0-9]|2[0-9])(?=\\d))$",
                message = "Regex for 'expireDate': yyyy-mm-dd"
        )
        String expireDate,

        @NotNull(message = "Passed null value as 'givenBy': ")
        @NotBlank(message = "Passed blank value as 'givenBy': ")
        @NotEmpty(message = "Passed empty value as 'givenBy': ")
        @Length(
                min = 3,
                max = 6,
                message = "'givenBy' length must be in range[3; 6]"
        )
        String givenBy
) {
}