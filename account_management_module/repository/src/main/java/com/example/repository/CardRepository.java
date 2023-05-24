package com.example.repository;

import com.example.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity,Long>{
    Optional<CardEntity> findCardEntityByCardNumber(String cardNumber);

    CardEntity findCardEntitiesByCardNumber(String cardNumber);


    @Transactional
    @Modifying
    @Query("update CardEntity c set c.isDeleted = true where c.cardNumber=:cardNumber")
    void updateIsDeleted( String cardNumber);


    @Transactional
    @Modifying
    @Query("update CardEntity c set c.status = 'DEACTIVATED' where c.cardNumber=:cardNumber")
    void deactivate(String cardNumber);

    @Transactional
    @Modifying
    @Query("update CardEntity c set c.status = 'ACTIVE' where c.cardNumber=:cardNumber")
    void activate(String cardNumber);

    @Transactional
    @Modifying
    @Query("update CardEntity c set c.status = 'BLOCKED' where c.cardNumber=:cardNumber")
    void block(String cardNumber);



}
