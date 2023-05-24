package com.bdg.cardholder_management_module.service.impl;

import com.bdg.cardholder_management_module.entity.CardHolderEntity;
import com.bdg.cardholder_management_module.entity.PassportEntity;
import com.bdg.cardholder_management_module.model.PassportModel;
import com.bdg.cardholder_management_module.repository.CardHolderRepository;
import com.bdg.cardholder_management_module.repository.PassportRepository;
import com.bdg.cardholder_management_module.service.PassportService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;
    private final CardHolderRepository cardHolderRepository;

    public PassportServiceImpl(
            PassportRepository passportRepository,
            CardHolderRepository cardHolderRepository) {
        this.passportRepository = passportRepository;
        this.cardHolderRepository = cardHolderRepository;
    }

    @Override
    public boolean save(PassportModel passportModel) {
        if (passportRepository.existsBySerialNumber(passportModel.getSerialNumber())) {
            throw new IllegalArgumentException("Serial number already exists: ");
        }

        PassportEntity passportEntity = new PassportEntity(passportModel);
        passportEntity.setCreatedOn(Date.valueOf(LocalDate.now()));
        passportEntity.setIsDeleted(false);

        passportRepository.save(passportEntity);
        return true;
    }

    @Override
    public boolean update(PassportModel passportModel) {
        Optional<PassportEntity> passportEntityOptional =
                passportRepository.findBySerialNumber(passportModel.getSerialNumber());

        if (passportEntityOptional.isPresent()) {
            if (passportEntityOptional.get().getIsDeleted()){
                passportEntityOptional.get().getFromModel(passportModel);
                passportEntityOptional.get().setIsDeleted(false);
                passportEntityOptional.get().setUpdatedOn(Date.valueOf(LocalDate.now()));
                return true;
            }
            throw new IllegalArgumentException("Passport with passed serial number already exists: ");
        }
        return false;
    }

    @Override
    public boolean delete(String serialNumber) {
//        Optional<CardHolderEntity> cardHolderEntity = cardHolderRepository.
//                findCardHolderEntityByPassport_SerialNumber(serialNumber);
//
//        if (cardHolderEntity.isPresent()) {
//            CardHolderEntity cardHolder = cardHolderEntity.get();
//            cardHolder.setIsDeleted(true);
//            cardHolder.getPassport().setIsDeleted(true);
//            return true;
//        }{
//            throw new IllegalArgumentException("no passport and card holder by this serial number");
//        }

        Optional<PassportEntity> optionalPassportEntity = passportRepository.findBySerialNumber(serialNumber);

        if (optionalPassportEntity.isPresent()){
            optionalPassportEntity.get().setIsDeleted(true);
            return true;
        }else {
            throw new IllegalArgumentException("Passport with passed serial number not found: ");
        }
    }
}