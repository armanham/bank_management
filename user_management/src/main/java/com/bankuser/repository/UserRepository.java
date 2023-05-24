package com.bankuser.repository;

import com.bankuser.model.entity.User;
import com.bankuser.model.proxy.UserP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<User, Long> {
    
    
    Optional<User> findByPassportNumber (String passportNumber);
    
    Optional<User> findByUserName (String userName);
    
    Optional<User> findByEmail (String email);
    
    Optional<User> findByPhoneNumber (String phone);
    
    ResponseEntity <UserP> signInViaEmail(String email, String password);
    
    ResponseEntity <UserP> signInViaPhoneNumber(String phoneNumber, String password);
    
    ResponseEntity <UserP> signInViaUserName(String UserName, String password);
    
}
