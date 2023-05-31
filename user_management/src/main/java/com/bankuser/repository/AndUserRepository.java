package com.bankuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankuser.model.entity.UserEntity;

@Repository
public interface AndUserRepository extends JpaRepository <UserEntity, Long> {
    
    public UserEntity getUserById (long id);
    
}