package com.bdg.cardholder_management_module.repository;

import com.bdg.cardholder_management_module.model.entity.PassportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassportRepository extends JpaRepository<PassportEntity, String> {

    boolean existsBySerialNumber(String serialNumber);

    Optional<PassportEntity> findPassportEntityBySerialNumber(String serialNumber);
}