package com.bdg.cardholder_management_module.service.impl;

import com.bdg.cardholder_management_module.entity.AddressEntity;
import com.bdg.cardholder_management_module.model.AddressModel;
import com.bdg.cardholder_management_module.repository.AddressRepository;
import com.bdg.cardholder_management_module.repository.CardHolderRepository;
import com.bdg.cardholder_management_module.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CardHolderRepository cardHolderRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository,
                              CardHolderRepository cardHolderRepository) {
        this.addressRepository = addressRepository;
        this.cardHolderRepository = cardHolderRepository;
    }

    @Override
    public boolean save(AddressModel addressModel) {
        Optional<AddressEntity> addressEntityOptional =
                addressRepository
                        .findAddressEntityByStreetAndCityAndCountry(addressModel.getStreet(), addressModel.getCity(), addressModel.getCountry());
        if (addressEntityOptional.isPresent()) {
            if (addressEntityOptional.get().getIsDeleted()) {
                addressEntityOptional.get().setIsDeleted(false);
            }
            return true;
        }

        AddressEntity addressEntity = new AddressEntity(addressModel);
        addressEntity.setIsDeleted(false);
        addressRepository.save(addressEntity);
        return true;
    }

    @Override
    public boolean update(AddressModel addressModel, long id) {
        Optional<AddressEntity> addressEntityOptionalToUpdate = addressRepository.findById(id);

        if (addressEntityOptionalToUpdate.isEmpty()) {
            throw new IllegalArgumentException("Address by passed id not found: ");
        }

        Optional<AddressEntity> optionalRepeatedAddressEntity =
                addressRepository.findAddressEntityByStreetAndCityAndCountry(
                        addressModel.getStreet(), addressModel.getCity(), addressModel.getCountry());

        if (optionalRepeatedAddressEntity.isPresent()){
            if(optionalRepeatedAddressEntity.get().getIsDeleted()){
                optionalRepeatedAddressEntity.get().setIsDeleted(false);
                optionalRepeatedAddressEntity.get().setUpdatedOn(Date.valueOf(LocalDate.now()));
                return true;
            }
            throw new IllegalArgumentException("Address already exists: ");
        }

        AddressEntity addressEntity = addressEntityOptionalToUpdate.get();
        addressEntity.getFromModel(addressModel);
        addressEntity.setUpdatedOn(Date.valueOf(LocalDate.now()));

        return true;
    }

    @Override
    public boolean delete(long id) {
        if (addressRepository.countOfAddressInCHAByAddressId(id) != 0){
            throw new IllegalArgumentException("On this address exists Card Holder: ");
        }

        Optional<AddressEntity> optionalAddressEntity = addressRepository.findById(id);
        if (optionalAddressEntity.isEmpty()) {
            throw new IllegalArgumentException("Address by passed id not found: ");
        }

        optionalAddressEntity.get().setIsDeleted(true);
        return true;
    }
}