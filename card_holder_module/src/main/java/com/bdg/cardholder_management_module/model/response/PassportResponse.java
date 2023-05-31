package com.bdg.cardholder_management_module.model.response;

import com.bdg.cardholder_management_module.model.dto.PassportModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "passport_info")
public record PassportResponse(

        @JsonProperty("serial_number")
        String serialNumber,

        @JsonProperty("nationality")
        String nationality,

        @JsonProperty("given_date")
        String givenDate,

        @JsonProperty("expire_date")
        String expireDate,

        @JsonProperty("given_by")
        String givenBy
) {
    public static PassportResponse getFromModel(PassportModel passportModel) {
        return new PassportResponse(
                passportModel.getSerialNumber(),
                passportModel.getNationality(),
                passportModel.getGivenDate(),
                passportModel.getExpireDate(),
                passportModel.getGivenBy()
        );
    }
}