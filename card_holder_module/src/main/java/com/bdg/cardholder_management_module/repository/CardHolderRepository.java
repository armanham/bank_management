package com.bdg.cardholder_management_module.repository;

import com.bdg.cardholder_management_module.entity.CardHolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardHolderRepository extends JpaRepository<CardHolderEntity, Long> {

    Optional<CardHolderEntity> findCardHolderEntityByPassport_SerialNumber(String serialNumber);

    Optional<CardHolderEntity> findCardHolderEntityByPhone(String phone);

    Optional<CardHolderEntity> findCardHolderEntityByEmail(String email);

    boolean existsCardHolderEntityByPhone(String phone);

    boolean existsCardHolderEntityByEmail(String email);
}