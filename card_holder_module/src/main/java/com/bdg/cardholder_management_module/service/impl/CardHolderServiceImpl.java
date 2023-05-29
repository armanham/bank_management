package com.bdg.cardholder_management_module.service.impl;


import com.bdg.cardholder_management_module.model.dto.AddressModel;
import com.bdg.cardholder_management_module.model.dto.CardHolderModel;
import com.bdg.cardholder_management_module.model.dto.PassportModel;
import com.bdg.cardholder_management_module.model.dto.PersonalInfoModel;
import com.bdg.cardholder_management_module.model.entity.AddressEntity;
import com.bdg.cardholder_management_module.model.entity.CardHolderEntity;
import com.bdg.cardholder_management_module.model.entity.PassportEntity;
import com.bdg.cardholder_management_module.repository.AddressRepository;
import com.bdg.cardholder_management_module.repository.CardHolderRepository;
import com.bdg.cardholder_management_module.repository.PassportRepository;
import com.bdg.cardholder_management_module.service.CardHolderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
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
        PassportEntity savedPassport = passportRepository.save(toSavePassportEntity);

        CardHolderEntity cardHolderEntity = new CardHolderEntity();
        cardHolderEntity.initializePersonalInfo(personalInfoModel);
        cardHolderEntity.setPassport(savedPassport);

        cardHolderRepository.save(cardHolderEntity);
        return true;
    }


    @Override
    public boolean activateCardHolder(String serialNumber) {
        CardHolderEntity cardHolderEntity = null;
        for (CardHolderEntity deletedCardHolder : cardHolderRepository.findDeletedCardHolders()) {
            if (deletedCardHolder.getPassport().getSerialNumber().equals(serialNumber)) {
                cardHolderEntity = deletedCardHolder;
                break;
            }
        }

        if (cardHolderEntity == null) {
            throw new IllegalArgumentException("No inactive card holder with passed passport number: ");
        }

        cardHolderEntity.setDeleted(false);
        cardHolderEntity.getPassport().setDeleted(false);
        for (AddressEntity address : cardHolderEntity.getAddresses()) {
            address.setDeleted(false);
        }
        return true;
    }


    @Override
    public boolean updatePersonalInfoBySerialNumber(String serialNumber, PersonalInfoModel personalInfoModel) {
        Optional<CardHolderEntity> optionalCardHolderEntityByPassportNumber =
                cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(serialNumber);

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
//            cardHolderEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));

            return true;
        }
    }


    @Override
    public boolean updatePassportBySerialNumber(String serialNumber, PassportModel passportModel) {
        Optional<PassportEntity> optionalPassportEntity =
                passportRepository.findPassportEntityBySerialNumber(serialNumber);

        if (optionalPassportEntity.isEmpty()) {
            throw new IllegalArgumentException("Passport with passed serial number not found: ");
        } else {
            PassportEntity passportEntity = optionalPassportEntity.get();

            if (passportEntity.getDeleted()) {
                throw new IllegalArgumentException("Passport with passed serial number is no longer active: ");
            }

            passportEntity.setPassportInfo(passportModel);
//            passportEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));

            cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(serialNumber)
                    .get()
                    .setUpdatedOn(Date.valueOf(LocalDate.now()));
        }
        return true;
    }


    @Override
    public boolean deleteCardHolderBySerialNumber(String serialNumber) {
        Optional<CardHolderEntity> optionalCardHolderEntityByPassportNumber =
                cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(serialNumber);

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
    public boolean addAddressOnCardHolder(String serialNumber, AddressModel addressModel) {
        Optional<CardHolderEntity> optionalCardHolderEntityByPassportNumber =
                cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(serialNumber);

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
//                cardHolderEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));
                return true;
            } else {
                AddressEntity toSaveAddressEntity = new AddressEntity(addressModel);
                toSaveAddressEntity.setDeleted(false);
//                toSaveAddressEntity.setCreatedOn(Date.valueOf(LocalDate.now()));

                cardHolderEntity.addAddress(addressRepository.save(toSaveAddressEntity));
//                cardHolderEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));
                return true;
            }
        }
    }


    @Override
    public boolean deleteAddressFromCardHolder(String serialNumber, AddressModel addressModel) {
        Optional<CardHolderEntity> optionalCardHolderEntityByPassportNumber =
                cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(serialNumber);

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
    public List<CardHolderModel> findDeletedCardHolders() {
        return cardHolderRepository
                .findDeletedCardHolders()
                .stream()
                .map(cardHolderEntity -> new CardHolderModel(
                        new PersonalInfoModel(cardHolderEntity),
                        new PassportModel(cardHolderEntity.getPassport()),
                        cardHolderEntity.getAddresses()
                                .stream()
                                .map(AddressModel::new)
                                .collect(Collectors.toSet())
                ))
                .toList();
    }


    @Override
    public List<CardHolderModel> findActiveCardHolders() {
        return cardHolderRepository
                .findActiveCardHolders()
                .stream()
                .map(cardHolderEntity -> new CardHolderModel(
                        new PersonalInfoModel(cardHolderEntity),
                        new PassportModel(cardHolderEntity.getPassport()),
                        cardHolderEntity.getAddresses()
                                .stream()
                                .map(AddressModel::new)
                                .collect(Collectors.toSet())
                ))
                .toList();
    }


    @Override
    public CardHolderModel findCardHolderBySerialNumber(String serialNumber) {
        Optional<CardHolderEntity> cardHolderEntityOptional =
                cardHolderRepository.findCardHolderEntityByPassport_SerialNumber(serialNumber);

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


    @Override
    public List<CardHolderModel> findCardHoldersByFullName(String firstName, String lastName) {
        return cardHolderRepository.
                findCardHolderEntitiesLikeFirstAndLastName(firstName, lastName)
                .stream()
                .map(cardHolderEntity -> new CardHolderModel(
                        new PersonalInfoModel(cardHolderEntity),
                        new PassportModel(cardHolderEntity.getPassport()),
                        cardHolderEntity.getAddresses()
                                .stream()
                                .map(AddressModel::new)
                                .collect(Collectors.toSet())
                ))
                .toList();
    }
}