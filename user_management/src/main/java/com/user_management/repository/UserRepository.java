package com.user_management.repository;

import com.user_management.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<User, Long> {
    
    
    Optional<User> findByPassportNumber (String passportNumber);
    
    Optional<User> findByUserName (String userName);
    
    Optional<User> findByEmail (String email);
    
    Optional<User> findByPhone (String phone);
    
    Optional<User> signInViaEmail(String email, String password);
    
    Optional<User> signInViaPhoneNumber(String phoneNumber, String password);
    
    Optional<User> signInViaUserName(String UserName, String password);
    
}
