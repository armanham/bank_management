package com.bdg.cardholder_management_module.response;

import com.bdg.cardholder_management_module.model.CardHolderModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Set;
import java.util.stream.Collectors;

@JsonRootName(value = "card_holder")
public record CardHolderResponse(
        @JsonProperty("personal_info")
        PersonalInfoResponse personalInfoResponse,

        @JsonProperty("passport_info")
        PassportResponse passportResponse,

        @JsonProperty("addresses")
        Set<AddressResponse> addressResponses
) {
    public static CardHolderResponse getFromModel(CardHolderModel cardHolderModel) {
        return new CardHolderResponse(
                PersonalInfoResponse.getFromModel(cardHolderModel.getPersonalInfoModel()),
                PassportResponse.getFromModel(cardHolderModel.getPassportModel()),
                cardHolderModel.getAddressModelList()
                        .stream()
                        .map(AddressResponse::getFromModel)
                        .collect(Collectors.toSet())
        );
    }
}