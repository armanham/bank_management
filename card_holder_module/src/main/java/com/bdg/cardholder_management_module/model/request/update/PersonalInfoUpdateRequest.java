package com.bdg.cardholder_management_module.model.request.update;

import com.bdg.cardholder_management_module.check.validator.annotation.NotBlankEmptyString;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.sql.Date;
import java.time.LocalDate;

public record PersonalInfoUpdateRequest(
        @NotBlankEmptyString
        @Pattern(
                regexp = "^[A-Z][A-Za-z\\s-]*$",
                message = "First name must start with uppercase"
        )
        @JsonProperty("first_name")
        String firstName,

        @NotBlankEmptyString
        @Pattern(
                regexp = "^[A-Z][A-Za-z\\s-]*$",
                message = "Last name must start with uppercase"
        )
        @JsonProperty("last_name")
        String lastName,

        @NotBlankEmptyString
        @Pattern(
                regexp = com.bdg.cardholder_management_module.check.pattern.Pattern.DATE_PATTERN,
                message = "Regex: yyyy-mm-dd"
        )
        @JsonProperty("date_of_birth")
        String dob,

        @NotBlankEmptyString
        @Pattern(
                regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$",
                message = "The phone number must be like these: \n" +
                        "123-456-7890\n" +
                        "(123) 456-7890\n" +
                        "123 456 7890\n" +
                        "123.456.7890\n" +
                        "+91 (123) 456-7890"
        )
        @JsonProperty("phone")
        String phone,

        @NotBlankEmptyString
        @Email
        @JsonProperty("email")
        String email,

        @NotBlankEmptyString
        @Pattern(
                regexp = "^(LEGAL|INDIVIDUAL)$",
                message = "Card holder type: LEGAL or INDIVIDUAL"
        )
        @JsonProperty("type")
        String cardHolderType
) {
    public PersonalInfoUpdateRequest {
        if (dob != null && LocalDate.now().isBefore(Date.valueOf(dob).toLocalDate())) {
            throw new IllegalArgumentException("Date of birth(dob) must be after date of now: ");
        }
    }
}