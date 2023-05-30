package com.bdg.cardholder_management_module.model.request.update;

import com.bdg.cardholder_management_module.check.validator.annotation.NotBlankEmptyString;
import com.bdg.cardholder_management_module.check.validator.annotation.NotNullEmptyBlankString;
import com.bdg.cardholder_management_module.check.validator.annotation.ValidPassportDates;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

@ValidPassportDates(message = "Invalid given/expire date for PassportUpdateRequest: ")
public record PassportUpdateRequest(

        @NotBlankEmptyString
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

        @NotBlankEmptyString
        @Length(
                min = 3,
                max = 6,
                message = "'givenBy' length must be in range[3; 6]"
        )
        @JsonProperty("given_by")
        String givenBy
) {
}