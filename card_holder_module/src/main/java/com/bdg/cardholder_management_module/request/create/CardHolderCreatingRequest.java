package com.bdg.cardholder_management_module.request.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;

public record CardHolderCreatingRequest(

        @Valid
        @JsonProperty("personal_info")
        PersonalInfoCreatingRequest personalInfoCreatingRequest,

        @Valid
        @JsonProperty("passport_info")
        PassportCreatingRequest passportCreatingRequest
) {

}