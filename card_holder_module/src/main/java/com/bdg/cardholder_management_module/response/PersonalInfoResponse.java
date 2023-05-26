package com.bdg.cardholder_management_module.response;

import com.bdg.cardholder_management_module.model.PersonalInfoModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "personal_info")
public record PersonalInfoResponse(
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @JsonProperty("date_of_birth")
        String dob,

        @JsonProperty("phone")
        String phone,

        @JsonProperty("email")
        String email,

        @JsonProperty("type")
        String cardHolderType
) {
    public static PersonalInfoResponse getFromModel(PersonalInfoModel personalInfoModel) {
        return new PersonalInfoResponse(
                personalInfoModel.getFirstName(),
                personalInfoModel.getLastName(),
                personalInfoModel.getDob(),
                personalInfoModel.getPhone(),
                personalInfoModel.getEmail(),
                personalInfoModel.getCardHolderType().toString()
        );
    }
}