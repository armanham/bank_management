package com.bdg.cardholder_management_module.request.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.time.LocalDate;

public record PassportCreatingRequest(

        @NotNull(message = "Passed null value as 'serialNumber': ")
        @NotBlank(message = "Passed blank value as 'serialNumber': ")
        @NotEmpty(message = "Passed empty value as 'serialNumber': ")
        @Pattern(
                regexp = "[A-Z]{2}\\d{7}",
                message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
        )
        @JsonProperty("serial_number")
        String serialNumber,

        @NotNull(message = "Passed null value as 'nationality': ")
        @NotBlank(message = "Passed blank value as 'nationality': ")
        @NotEmpty(message = "Passed empty value as 'nationality': ")
        @Length(
                min = 2,
                max = 32,
                message = "'nationality' length must be in range[2; 32]"
        )
        @JsonProperty("nationality")
        String nationality,

        @NotNull(message = "Passed null value as 'givenDate': ")
        @NotBlank(message = "Passed blank value as 'givenDate': ")
        @NotEmpty(message = "Passed empty value as 'givenDate': ")
        @Pattern(
                regexp = com.bdg.cardholder_management_module.check.pattern.Pattern.DATE_PATTERN,
                message = "Regex for 'givenDate': yyyy-mm-dd"
        )
        @JsonProperty("given_date")
        String givenDate,

        @NotNull(message = "Passed null value as 'expireDate': ")
        @NotBlank(message = "Passed blank value as 'expireDate': ")
        @NotEmpty(message = "Passed empty value as 'expireDate': ")
        @Pattern(
                regexp = com.bdg.cardholder_management_module.check.pattern.Pattern.DATE_PATTERN,
                message = "Regex for 'expireDate': yyyy-mm-dd"
        )
        @JsonProperty("expire_date")
        String expireDate,

        @NotNull(message = "Passed null value as 'givenBy': ")
        @NotBlank(message = "Passed blank value as 'givenBy': ")
        @NotEmpty(message = "Passed empty value as 'givenBy': ")
        @Length(
                min = 3,
                max = 6,
                message = "'givenBy' length must be in range[3; 6]"
        )
        @JsonProperty("given_by")
        String givenBy
) {
    public PassportCreatingRequest {
        if (LocalDate.now().isBefore(Date.valueOf(givenDate).toLocalDate())) {
            throw new IllegalArgumentException("Date of givenDate must be after date of now: ");
        }
        if (Date.valueOf(givenDate).toLocalDate().isAfter(Date.valueOf(expireDate).toLocalDate())) {
            throw new IllegalArgumentException("Date of givenDate must be before date of expireDate: ");
        }
    }
}