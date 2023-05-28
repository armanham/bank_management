package com.example.repository;

import com.example.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



import com.example.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity,Long> {

    Optional<AccountEntity> findAccountEntityByAccountNumber(String accountNumber);

    @Transactional
    @Modifying
    @Query("update AccountEntity a set a.isDeleted = true where a.accountNumber=:accountNumber")
    void updateIsDeleted( String accountNumber);


    @Transactional
    @Modifying
    @Query("update AccountEntity a set a.status = 'DEACTIVATED' where a.accountNumber=:accountNumber")
    void deactivate(String accountNumber);

    @Transactional
    @Modifying
    @Query("update AccountEntity a set a.status = 'ACTIVE' where a.accountNumber=:accountNumber")
    void activate(String accountNumber);

    @Transactional
    @Modifying
    @Query("update AccountEntity a set a.status = 'BLOCKED' where a.accountNumber=:accountNumber")
    void block(String accountNumber);
}
