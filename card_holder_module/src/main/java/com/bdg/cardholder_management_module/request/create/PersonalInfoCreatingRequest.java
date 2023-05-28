package com.bdg.cardholder_management_module.request.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.sql.Date;
import java.time.LocalDate;


public record PersonalInfoCreatingRequest(

        @NotNull(message = "Passed null value as 'firstName': ")
        @NotBlank(message = "Passed blank value as 'firstName': ")
        @NotEmpty(message = "Passed empty value as 'firstName': ")
        @Pattern(
                regexp = "^[A-Z][A-Za-z\\s-]*$",
                message = "First name must start with uppercase"
        )
        @JsonProperty("first_name")
        String firstName,

        @NotNull(message = "Passed null value as 'lastName': ")
        @NotBlank(message = "Passed blank value as 'lastName': ")
        @NotEmpty(message = "Passed empty value as 'lastName': ")
        @Pattern(
                regexp = "^[A-Z][A-Za-z\\s-]*$",
                message = "Last name must start with uppercase"
        )
        @JsonProperty("last_name")
        String lastName,

        @NotNull(message = "Passed null value as 'dob(date of birth)': ")
        @NotBlank(message = "Passed blank value as 'dob(date of birth)': ")
        @NotEmpty(message = "Passed empty value as 'dob(date of birth)': ")
        @Pattern(
                regexp = com.bdg.cardholder_management_module.check.pattern.Pattern.DATE_PATTERN,
                message = "Regex: yyyy-mm-dd"
        )
        @JsonProperty("date_of_birth")
        String dob,

        @NotNull(message = "Passed null value as 'phone': ")
        @NotBlank(message = "Passed blank value as 'phone': ")
        @NotEmpty(message = "Passed empty value as 'phone': ")
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

        @NotNull(message = "Passed null value as 'email': ")
        @NotBlank(message = "Passed blank value as 'email': ")
        @NotEmpty(message = "Passed empty value as 'email': ")
        @Email
        @JsonProperty("email")
        String email,

        @NotNull(message = "Passed null value as 'cardHolderType': ")
        @NotBlank(message = "Passed blank value as 'cardHolderType': ")
        @NotEmpty(message = "Passed empty value as 'cardHolderType': ")
        @Pattern(
                regexp = "^(LEGAL|INDIVIDUAL)$",
                message = "Card holder type: LEGAL or INDIVIDUAL"
        )
        @JsonProperty("type")
        String cardHolderType
) {
    public PersonalInfoCreatingRequest {
        if (LocalDate.now().isBefore(Date.valueOf(dob).toLocalDate())) {
            throw new IllegalArgumentException("Date of birth(dob) must be after date of now: ");
        }
    }
}