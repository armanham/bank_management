package com.example.repository;

import com.example.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

    Optional<TransactionEntity> findTransactionEntityByTransactionNumber(String transactionNumber);

}
