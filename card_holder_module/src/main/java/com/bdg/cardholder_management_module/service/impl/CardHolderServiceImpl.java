package com.bdg.cardholder_management_module.service.impl;


import com.bdg.cardholder_management_module.entity.AddressEntity;
import com.bdg.cardholder_management_module.entity.CardHolderEntity;
import com.bdg.cardholder_management_module.entity.PassportEntity;
import com.bdg.cardholder_management_module.model.AddressModel;
import com.bdg.cardholder_management_module.model.CardHolderModel;
import com.bdg.cardholder_management_module.model.PassportModel;
import com.bdg.cardholder_management_module.model.PersonalInfoModel;
import com.bdg.cardholder_management_module.repository.AddressRepository;
import com.bdg.cardholder_management_module.repository.CardHolderRepository;
import com.bdg.cardholder_management_module.repository.PassportRepository;
import com.bdg.cardholder_management_module.service.CardHolderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class CardHolderServiceImpl implements CardHolderService {

    private final CardHolderRepository cardHolderRepository;
    private final PassportRepository passportRepository;
    private final AddressRepository addressRepository;


    @Autowired
    public CardHolderServiceImpl(
            CardHolderRepository cardHolderRepository,
            PassportRepository passportRepository,
            AddressRepository addressRepository) {
        this.cardHolderRepository = cardHolderRepository;
        this.addressRepository = addressRepository;
        this.passportRepository = passportRepository;
    }


    @Override
    public boolean saveCardHolder(PersonalInfoModel personalInfoModel, PassportModel passportModel) {
        if (
                cardHolderRepository.existsCardHolderEntityByPhone(personalInfoModel.getPhone())
                        || cardHolderRepository.existsCardHolderEntityByEmail(personalInfoModel.getEmail())) {
            throw new IllegalArgumentException("Passed phone or email already exists: ");
        }

        if (passportRepository.existsBySerialNumber(passportModel.getSerialNumber())) {
            throw new IllegalArgumentException("Serial number already exists: ");
        }

        PassportEntity toSavePassportEntity = new PassportEntity(passportModel);
        toSavePassportEntity.setCreatedOn(Date.valueOf(LocalDate.now()));
        toSavePassportEntity.setDeleted(false);

        PassportEntity savedPassport = passportRepository.save(toSavePassportEntity);

        CardHolderEntity cardHolderEntity = new CardHolderEntity();

        cardHolderEntity.setPersonalInfo(personalInfoModel);
        cardHolderEntity.setPassport(savedPassport);
        cardHolderEntity.setCreatedOn(Date.valueOf(LocalDate.now()));
        cardHolderEntity.setDeleted(false);

        cardHolderRepository.save(cardHolderEntity);
        return true;
    }


    @Override
    public boolean activateCardHolder(String passportNo) {
        Optional<CardHolderEntity> optionalCardHolderEntity =
                cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(passportNo);

        if (optionalCardHolderEntity.isEmpty()) {
            throw new IllegalArgumentException("No card holder with passed passport number: ");
        }

        CardHolderEntity cardHolderEntity = optionalCardHolderEntity.get();
        if (cardHolderEntity.getDeleted()) {
            cardHolderEntity.setDeleted(false);
            cardHolderEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));

            cardHolderEntity.getPassport().setDeleted(false);
            cardHolderEntity.getPassport().setUpdatedOn(Date.valueOf(LocalDate.now()));

            for (AddressEntity address : cardHolderEntity.getAddresses()) {
                address.setDeleted(false);
                address.setUpdatedOn(Date.valueOf(LocalDate.now()));
            }

            return true;
        } else {
            throw new IllegalArgumentException("Card holder is already active: ");
        }
    }


    @Override
    public boolean updatePersonalInfoByPassportNumber(String passportNo, PersonalInfoModel personalInfoModel) {
        Optional<CardHolderEntity> optionalCardHolderEntityByPassportNumber =
                cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(passportNo);

        if (optionalCardHolderEntityByPassportNumber.isEmpty()) {
            throw new IllegalArgumentException("No card holder with passed passport number: ");
        } else {
            CardHolderEntity cardHolderEntity = optionalCardHolderEntityByPassportNumber.get();

            if (cardHolderEntity.getDeleted()) {
                throw new IllegalArgumentException("Card holder is already deleted with passed passport number: ");
            }

            if (cardHolderRepository.existsCardHolderEntityByEmail(personalInfoModel.getEmail())) {
                throw new IllegalArgumentException("Email already exists: ");
            }
            if (cardHolderRepository.existsCardHolderEntityByPhone(personalInfoModel.getPhone())) {
                throw new IllegalArgumentException("Phone number already exists: ");
            }

            cardHolderEntity.setPersonalInfo(personalInfoModel);
            cardHolderEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));

            return true;
        }
    }


    @Override
    public boolean updatePassportByPassportNumber(String passportNo, PassportModel passportModel) {
        Optional<PassportEntity> optionalPassportEntity =
                passportRepository.findPassportEntityBySerialNumber(passportNo);

        if (optionalPassportEntity.isEmpty()) {
            throw new IllegalArgumentException("Passport with passed serial number not found: ");
        } else {
            PassportEntity passportEntity = optionalPassportEntity.get();

            if (passportEntity.getDeleted()) {
                throw new IllegalArgumentException("Passport with passed serial number is no longer active: ");
            }

            passportEntity.setPassportInfo(passportModel);
            passportEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));

            cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(passportNo)
                    .get()
                    .setUpdatedOn(Date.valueOf(LocalDate.now()));
        }
        return true;
    }


    @Override
    public boolean deleteCardHolderByPassportNumber(String passportNo) {
        Optional<CardHolderEntity> optionalCardHolderEntityByPassportNumber =
                cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(passportNo);

        if (optionalCardHolderEntityByPassportNumber.isEmpty()) {
            throw new IllegalArgumentException("No card holder with passed passport number: ");
        } else {
            CardHolderEntity cardHolderEntity = optionalCardHolderEntityByPassportNumber.get();

            if (cardHolderEntity.getDeleted()) {
                throw new IllegalArgumentException("Card holder is already deleted with passed passport number: ");
            }

            cardHolderEntity.getPassport().setDeleted(true);
            cardHolderEntity.getPassport().setUpdatedOn(Date.valueOf(LocalDate.now()));
            cardHolderEntity.setDeleted(true);
            cardHolderEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));

            for (AddressEntity addressEntity : cardHolderEntity.getAddresses()) {
                if (cardHolderRepository.occurrenceInCHA(Math.toIntExact(addressEntity.getId())) == 1) {
                    addressEntity.setDeleted(true);
                    addressEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));
                }
            }
            cardHolderRepository.
                    deleteCardHolderWithAddressFromCHA(cardHolderEntity.getId());

            return true;
        }
    }


    @Override
    public boolean addAddressOnCardHolder(String passportNo, AddressModel addressModel) {
        Optional<CardHolderEntity> optionalCardHolderEntityByPassportNumber =
                cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(passportNo);

        if (optionalCardHolderEntityByPassportNumber.isEmpty()) {
            throw new IllegalArgumentException("No card holder with passed passport number: ");
        } else {
            CardHolderEntity cardHolderEntity = optionalCardHolderEntityByPassportNumber.get();

            if (cardHolderEntity.getDeleted()) {
                throw new IllegalArgumentException("Card holder is already deleted with passed passport number: ");
            }

            Optional<AddressEntity> addressEntityOptional =
                    addressRepository.findAddressEntityByStreetAndCityAndCountry(
                            addressModel.getStreet(), addressModel.getCity(), addressModel.getCountry());

            if (addressEntityOptional.isPresent()) {
                AddressEntity addressEntity = addressEntityOptional.get();
                if (addressEntity.getDeleted()) {
                    addressEntity.setDeleted(false);
                    addressEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));
                }

                cardHolderEntity.addAddress(addressEntity);
                cardHolderEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));
                return true;
            } else {
                AddressEntity toSaveAddressEntity = new AddressEntity(addressModel);
                toSaveAddressEntity.setDeleted(false);
                toSaveAddressEntity.setCreatedOn(Date.valueOf(LocalDate.now()));

                cardHolderEntity.addAddress(addressRepository.save(toSaveAddressEntity));
                cardHolderEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));
                return true;
            }
        }
    }


    @Override
    public boolean deleteAddressFromCardHolder(String passportNo, AddressModel addressModel) {
        Optional<CardHolderEntity> optionalCardHolderEntityByPassportNumber =
                cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(passportNo);

        Optional<AddressEntity> addressEntityOptional = addressRepository.findAddressEntityByStreetAndCityAndCountry(
                addressModel.getStreet(), addressModel.getCity(), addressModel.getCountry());

        if (optionalCardHolderEntityByPassportNumber.isEmpty()) {
            throw new IllegalArgumentException("No card holder with passed passport number: ");
        }

        CardHolderEntity cardHolderEntity = optionalCardHolderEntityByPassportNumber.get();
        if (cardHolderEntity.getDeleted()) {
            throw new IllegalArgumentException("Card holder with passed passport number is no longer active: ");
        }

        if (addressEntityOptional.isEmpty()) {
            throw new IllegalArgumentException("Address does not exist: ");
        }

        AddressEntity addressEntity = addressEntityOptional.get();
        if (addressEntity.getDeleted()) {
            throw new IllegalArgumentException("Address is no longer active: ");
        }

        if (cardHolderEntity.getAddresses().remove(addressEntity)) {
            cardHolderEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));
        }

        if (addressEntity.getCardHolders().size() == 1) {
            addressEntity.setDeleted(true);
            addressEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));
        }
        return true;
    }


    @Override
    public CardHolderModel findCardHolderByPassportNo(String passportNo) {
        Optional<CardHolderEntity> cardHolderEntityOptional =
                cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(passportNo);

        if (cardHolderEntityOptional.isEmpty()) {
            throw new IllegalArgumentException("There is no card holder with the given serial number");
        }

        CardHolderEntity cardHolderEntity = cardHolderEntityOptional.get();
        if (cardHolderEntity.getDeleted()) {
            throw new IllegalArgumentException("Card Holder with the given serial number is no longer active");
        }

        return new CardHolderModel(
                new PersonalInfoModel(cardHolderEntity),
                new PassportModel(cardHolderEntity.getPassport()),
                cardHolderEntity.getAddresses()
                        .stream()
                        .map(AddressModel::new)
                        .collect(Collectors.toSet())
        );
    }


    @Override
    public CardHolderModel findCardHolderByPhone(String phone) {
        Optional<CardHolderEntity> cardHolderEntityOptional =
                cardHolderRepository.findCardHolderEntityByPhone(phone);

        if (cardHolderEntityOptional.isEmpty()) {
            throw new IllegalArgumentException("There is no card holder with the given phone number");
        }

        CardHolderEntity cardHolderEntity = cardHolderEntityOptional.get();
        if (cardHolderEntity.getDeleted()) {
            throw new IllegalArgumentException("Card Holder with the given phone number is no longer active");
        }

        return new CardHolderModel(
                new PersonalInfoModel(cardHolderEntity),
                new PassportModel(cardHolderEntity.getPassport()),
                cardHolderEntity.getAddresses()
                        .stream()
                        .map(AddressModel::new)
                        .collect(Collectors.toSet())
        );
    }


    @Override
    public CardHolderModel findCardHolderByEmail(String email) {
        Optional<CardHolderEntity> cardHolderEntityOptional =
                cardHolderRepository.findCardHolderEntityByEmail(email);

        if (cardHolderEntityOptional.isEmpty()) {
            throw new IllegalArgumentException("There is no card holder with the given email");
        }

        CardHolderEntity cardHolderEntity = cardHolderEntityOptional.get();
        if (cardHolderEntity.getDeleted()) {
            throw new IllegalArgumentException("Card Holder with the given email is no longer active");
        }

        return new CardHolderModel(
                new PersonalInfoModel(cardHolderEntity),
                new PassportModel(cardHolderEntity.getPassport()),
                cardHolderEntity.getAddresses()
                        .stream()
                        .map(AddressModel::new)
                        .collect(Collectors.toSet())
        );
    }
}