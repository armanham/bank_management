package com.bdg.cardholder_management_module.repository;

import com.bdg.cardholder_management_module.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    boolean existsAddressEntityByStreetAndCityAndCountry(
            String street, String city, String country);

    Optional<AddressEntity> findAddressEntityByStreetAndCityAndCountry(
            String street, String city, String country);

    List<AddressEntity> findAddressByCity(String city);

    List<AddressEntity> findAddressByCountry(String country);

    List<AddressEntity> findAddressByStreet(String street);


    @Query(
            value = "select count(cha) from card_holder_address where cha.address_id = ?1",
            nativeQuery = true
    )
    int countOfAddressInCHAByAddressId(Long id);


}