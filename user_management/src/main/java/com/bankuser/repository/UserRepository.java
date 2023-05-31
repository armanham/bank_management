package com.bankuser.repository;


import com.bankuser.model.entity.UserEntity;
import com.bankuser.model.proxy.UserP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository <UserEntity, Long> {
    
    Optional <UserEntity> findByPassportNumber (final String passportNumber);
    
    Optional <UserEntity> findByUsername (final String userName);
    
    Optional <UserEntity> findByEmail (final String email);
    
    Optional <UserEntity> findByPhoneNumber (final String phone);
    
    ResponseEntity <UserP> signInViaEmail (final String email, final String password);
    
    ResponseEntity <UserP> signInViaPhoneNumber (final String phoneNumber, final String password);
    
    ResponseEntity <UserP> signInViaUsername (final String UserName, final String password);
    
    ResponseEntity <UserP> update (final UserP userP);
    
    ResponseEntity <UserP> forgotPasswordViaEmail (final String email);
    
    ResponseEntity <UserP> forgotPasswordViaPhoneNumber (final String phoneNumber);
    
    ResponseEntity <UserP> forgotPasswordViaUsername (final String username);
    
    ResponseEntity <UserP> resetPasswordViaEmail (final String email, final String password);
    
    ResponseEntity <UserP> resetPasswordViaPhoneNumber (final String phoneNumber, final String password);
    
    ResponseEntity <UserP> resetPasswordViaUsername (final String username, final String password);
    
}