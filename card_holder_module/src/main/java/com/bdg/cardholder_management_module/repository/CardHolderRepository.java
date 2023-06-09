package com.bdg.cardholder_management_module.repository;

import com.bdg.cardholder_management_module.model.entity.CardHolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardHolderRepository extends JpaRepository<CardHolderEntity, Long> {

    Optional<CardHolderEntity> findCardHolderEntityByPassport_SerialNumber(String serialNumber);

    Optional<CardHolderEntity> findCardHolderEntityByPhone(String phone);

    Optional<CardHolderEntity> findCardHolderEntityByEmail(String email);

    List<CardHolderEntity> findCardHolderEntitiesByFirstName(String firstName);

    List<CardHolderEntity> findCardHolderEntitiesByLastName(String lastName);

    boolean existsCardHolderEntityByPhone(String phone);

    boolean existsCardHolderEntityByEmail(String email);


    @Modifying
    @Query(
            nativeQuery = true,
            value = "delete from card_holder_address " +
                    "where card_holder_id = :card_holder_id "
    )
    void deleteCardHolderWithAddressFromCHA(
            @Param("card_holder_id") Long cardHolderId
    );


    @Query(
            nativeQuery = true,
            value = "SELECT COUNT(*) " +
                    "FROM card_holder_address " +
                    "WHERE address_id = :address_id "
    )
    int occurrenceInCHA(
            @Param("address_id") int addressId
    );

    @Query(value = "select c from CardHolderEntity as c where c.firstName like CONCAT(:firstName, '%') ")
    List<CardHolderEntity> findCardHolderEntitiesLikeFirstName(@Param("firstName") String firstName);

    @Query(value = "select c from CardHolderEntity as c where c.lastName like CONCAT(:lastName, '%') ")
    List<CardHolderEntity> findCardHolderEntitiesLikeLastName(@Param("lastName") String lastName);

    @Query(value = "select c from CardHolderEntity as c where c.isDeleted = true ")
    List<CardHolderEntity> findDeletedCardHolders();

    @Query(value = "select c from CardHolderEntity as c where c.isDeleted = false ")
    List<CardHolderEntity> findActiveCardHolders();

    @Query(value = "select c from CardHolderEntity as c where " +
            "c.firstName like CONCAT(:firstName, '%') and " +
            "c.lastName like CONCAT(:lastName, '%') ")
    List<CardHolderEntity> findCardHolderEntitiesLikeFirstAndLastName(@Param("firstName") String firstName,
                                                                      @Param("lastName") String lastName);

}