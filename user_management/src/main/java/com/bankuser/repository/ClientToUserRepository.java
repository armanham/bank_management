package com.bankuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankuser.model.entity.ClientToUser;

public interface ClientToUserRepository extends JpaRepository<ClientToUser,Long>{

}
