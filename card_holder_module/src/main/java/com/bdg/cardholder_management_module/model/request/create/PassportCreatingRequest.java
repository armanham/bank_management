package com.bdg.cardholder_management_module.model.request.create;

import com.bdg.cardholder_management_module.check.validator.annotation.NotNullEmptyBlankString;
import com.bdg.cardholder_management_module.check.validator.annotation.ValidPassportDates;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

@ValidPassportDates(message = "Invalid given/expire date for PassportCreatingRequest: ")
public record PassportCreatingRequest(

        @NotNullEmptyBlankString
        @Pattern(
                regexp = "[A-Z]{2}\\d{7}",
                message = "'serialNumber' must have 2 uppercase letters followed by 7 digits"
        )
        @JsonProperty("serial_number")
        String serialNumber,

        @NotNullEmptyBlankString
        @Length(
                min = 2,
                max = 32,
                message = "'nationality' length must be in range[2; 32]"
        )
        @JsonProperty("nationality")
        String nationality,

        @NotNullEmptyBlankString
        @Pattern(
                regexp = com.bdg.cardholder_management_module.check.pattern.Pattern.DATE_PATTERN,
                message = "Regex for 'givenDate': yyyy-mm-dd"
        )
        @JsonProperty("given_date")
        String givenDate,

        @NotNullEmptyBlankString
        @Pattern(
                regexp = com.bdg.cardholder_management_module.check.pattern.Pattern.DATE_PATTERN,
                message = "Regex for 'expireDate': yyyy-mm-dd"
        )
        @JsonProperty("expire_date")
        String expireDate,

        @NotNullEmptyBlankString
        @Length(
                min = 3,
                max = 6,
                message = "'givenBy' length must be in range[3; 6]"
        )
        @JsonProperty("given_by")
        String givenBy
) {
}