package com.bdg.cardholder_management_module.request;

import com.bdg.cardholder_management_module.entity.CardHolderType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;


public record CardHolderRequest(

        @NotNull(message = "Passed null value as 'firstName': ")
        @NotBlank(message = "Passed blank value as 'firstName': ")
        @NotEmpty(message = "Passed empty value as 'firstName': ")
        @Pattern(
                regexp = "^[A-Z][A-Za-z\\s-]*$",
                message = "First name must start with uppercase"
        )
        String firstName,

        @NotNull(message = "Passed null value as 'lastName': ")
        @NotBlank(message = "Passed blank value as 'lastName': ")
        @NotEmpty(message = "Passed empty value as 'lastName': ")
        @Pattern(
                regexp = "^[A-Z][A-Za-z\\s-]*$",
                message = "Last name must start with uppercase"
        )
        String lastName,

        @NotNull(message = "Passed null value as 'dob(date of birth)': ")
        @NotBlank(message = "Passed blank value as 'dob(date of birth)': ")
        @NotEmpty(message = "Passed empty value as 'dob(date of birth)': ")
        @Pattern(
                regexp = "^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|1\\d|2[0-8]|(0[1-9]|1[0-9]|2[0-9])(?=\\d))$",
                message = "Regex: yyyy-mm-dd"
        )
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
        String phone,

        @NotNull(message = "Passed null value as 'email': ")
        @NotBlank(message = "Passed blank value as 'email': ")
        @NotEmpty(message = "Passed empty value as 'email': ")
        @Email
        String email,

        @NotNull(message = "Passed null value as 'cardHolderType': ")
        @NotBlank(message = "Passed blank value as 'cardHolderType': ")
        @NotEmpty(message = "Passed empty value as 'cardHolderType': ")
        @Pattern(
                regexp = "^(LEGAL|INDIVIDUAL)$",
                message = "Card holder type: LEGAL or INDIVIDUAL"
        )
        String cardHolderType
        ) {
}