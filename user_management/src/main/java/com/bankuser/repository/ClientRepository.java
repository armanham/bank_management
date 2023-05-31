package com.bankuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankuser.model.entity.Client;

public interface ClientRepository extends JpaRepository <Client, Long> {

}