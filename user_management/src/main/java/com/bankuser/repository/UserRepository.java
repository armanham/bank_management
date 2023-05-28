package com.bankuser.repository;

import com.bankuser.model.entity.UserEntity;
import com.bankuser.model.proxy.UserP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    
    Optional<UserEntity> findByPassportNumber (final String passportNumber);
    
    Optional<UserEntity> findByUsername (final String userName);
    
    Optional<UserEntity> findByEmail (final String email);
    
    Optional<UserEntity> findByPhoneNumber (final String phone);
    
    ResponseEntity <UserP> signInViaEmail(final String email, final String password);
    
    ResponseEntity <UserP> signInViaPhoneNumber(final String phoneNumber, final String password);
    
    ResponseEntity <UserP> signInViaUserName(final String UserName, final String password);
    
}
