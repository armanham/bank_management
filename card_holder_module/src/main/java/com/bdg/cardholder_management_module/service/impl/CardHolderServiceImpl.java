package com.bdg.cardholder_management_module.service.impl;


import com.bdg.cardholder_management_module.entity.AddressEntity;
import com.bdg.cardholder_management_module.entity.CardHolderEntity;
import com.bdg.cardholder_management_module.entity.PassportEntity;
import com.bdg.cardholder_management_module.model.AddressModel;
import com.bdg.cardholder_management_module.model.CardHolderModel;
import com.bdg.cardholder_management_module.model.PassportModel;
import com.bdg.cardholder_management_module.repository.AddressRepository;
import com.bdg.cardholder_management_module.repository.CardHolderRepository;
import com.bdg.cardholder_management_module.repository.PassportRepository;
import com.bdg.cardholder_management_module.service.AddressService;
import com.bdg.cardholder_management_module.service.CardHolderService;
import com.bdg.cardholder_management_module.service.PassportService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;


@Service
@Transactional
public class CardHolderServiceImpl implements CardHolderService {

    private final CardHolderRepository cardHolderRepository;
    private final PassportRepository passportRepository;
    private final AddressService addressService;
    private final AddressRepository addressRepository;
    private final PassportService passportService;

    @Autowired
    public CardHolderServiceImpl(
            CardHolderRepository cardHolderRepository,
            PassportRepository passportRepository,
            AddressService addressService,
            AddressRepository addressRepository,
            PassportService passportService) {
        this.cardHolderRepository = cardHolderRepository;
        this.addressService = addressService;
        this.addressRepository = addressRepository;
        this.passportService = passportService;
        this.passportRepository = passportRepository;
    }

    @Override
    public boolean save(CardHolderModel cardHolderModel, PassportModel passportModel) {
        if (
                cardHolderRepository.existsCardHolderEntityByPhone(cardHolderModel.getPhone())
                        || cardHolderRepository.existsCardHolderEntityByEmail(cardHolderModel.getEmail())) {
            throw new IllegalArgumentException("Passed phone or email already exists: ");
        }

        if (passportRepository.existsBySerialNumber(passportModel.getSerialNumber())) {
            throw new IllegalArgumentException("Serial number already exists: ");
        }

        PassportEntity passportEntity = new PassportEntity(passportModel);
        passportEntity.setCreatedOn(Date.valueOf(LocalDate.now()));
        passportEntity.setIsDeleted(false);

        PassportEntity savedPassport = passportRepository.save(passportEntity);
        CardHolderEntity cardHolderEntity = new CardHolderEntity(cardHolderModel);
        cardHolderEntity.setPassport(savedPassport);
        cardHolderEntity.setCreatedOn(Date.valueOf(LocalDate.now()));
        cardHolderEntity.setIsDeleted(false);

        cardHolderRepository.save(cardHolderEntity);
        return true;
    }


    @Override
    public boolean updatePersonalInfo(String passportNo, CardHolderModel cardHolderModel) {
        Optional<CardHolderEntity> optionalCardHolderEntityByPassportNumber =
                cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(passportNo);

        if (optionalCardHolderEntityByPassportNumber.isEmpty()) {
            throw new IllegalArgumentException("No card holder with passed passport number: ");
        } else {
            CardHolderEntity cardHolderEntity = optionalCardHolderEntityByPassportNumber.get();

            if (!cardHolderModel.getDob().equals(cardHolderEntity.getDob().toString())){
                throw new IllegalArgumentException("Date of birth is not updatable: ");
            }
            if (cardHolderRepository.existsCardHolderEntityByEmail(cardHolderModel.getEmail())){
                throw new IllegalArgumentException("Email already exists: ");
            }
            if (cardHolderRepository.existsCardHolderEntityByPhone(cardHolderModel.getPhone())){
                throw new IllegalArgumentException("Phone number already exists: ");
            }

            if (cardHolderEntity.getIsDeleted()) {
                cardHolderEntity.setIsDeleted(false);
            }
            cardHolderEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));
            cardHolderEntity.getFromModel(cardHolderModel);

            return true;
        }
    }

    @Override
    public boolean delete(String passportNo) {
        Optional<CardHolderEntity> optionalCardHolderEntityByPassportNumber =
                cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(passportNo);

        if (optionalCardHolderEntityByPassportNumber.isEmpty()) {
            throw new IllegalArgumentException("No card holder with passed passport number: ");
        } else {
            CardHolderEntity cardHolderEntity = optionalCardHolderEntityByPassportNumber.get();
            if (cardHolderEntity.getIsDeleted()) {
                throw new IllegalArgumentException("Card holder is already deleted with passed passport number: ");
            }
            cardHolderEntity.setIsDeleted(true);
            cardHolderEntity.getPassport().setIsDeleted(true);

//            if(addressRepository.countOfAddressInCHAByAddressId(cardHolderEntity.getAddresses().))

            return true;
        }
    }


    @Override
    public boolean addAddress(String passportNo, AddressModel addressModel) {
        Optional<CardHolderEntity> optionalCardHolderEntityByPassportNumber =
                cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(passportNo);

        if (optionalCardHolderEntityByPassportNumber.isEmpty()) {
            throw new IllegalArgumentException("No card holder with passed passport number: ");
        } else {
            CardHolderEntity cardHolderEntity = optionalCardHolderEntityByPassportNumber.get();
            if (cardHolderEntity.getIsDeleted()) {
                throw new IllegalArgumentException("Card holder is already deleted with passed passport number: ");
            }

            Optional<AddressEntity> addressEntityOptional =
                    addressRepository.findAddressEntityByStreetAndCityAndCountry(
                            addressModel.getStreet(), addressModel.getCity(), addressModel.getCountry());

            if (addressEntityOptional.isPresent()) {
                AddressEntity addressEntity = addressEntityOptional.get();
                if (addressEntity.getIsDeleted()) {
                    addressEntity.setIsDeleted(false);
                    addressEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));
                }

                cardHolderEntity.addAddress(addressEntity);
                cardHolderEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));
                return true;
            } else {
                AddressEntity toSaveAddressEntity = new AddressEntity(addressModel);
                toSaveAddressEntity.setIsDeleted(false);
                toSaveAddressEntity.setCreatedOn(Date.valueOf(LocalDate.now()));

                cardHolderEntity.addAddress(addressRepository.save(toSaveAddressEntity));
                cardHolderEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));
                return true;
            }
        }
    }
}