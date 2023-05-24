package com.bankuser.repository.imp;

import com.bankuser.model.proxy.UserP;
import jakarta.persistence.EntityManager;
import com.bankuser.model.entity.User;
import com.bankuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl extends SimpleJpaRepository<User, Long> implements UserRepository {
    
    private final EntityManager entityManager;
    
    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        super(User.class, entityManager);
        this.entityManager = entityManager;
    }
    
    @Override
    public Optional<User> findByPassportNumber(String passportNumber) {
        String jpql = "SELECT u FROM User u inner join Passport p WHERE p.number = :passportNumber";
        return entityManager.createQuery(jpql, User.class)
                .setParameter("passportNumber", passportNumber)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    @Override
    public Optional <User> findByUserName (String userName) {
        String jpql = "SELECT u FROM User u  WHERE u.username = :userName";
        return entityManager.createQuery(jpql, User.class)
                .setParameter("userName", userName)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    @Override
    public Optional <User> findByEmail (String email) {
        String jpql = "SELECT u FROM User u  WHERE u.email = :email";
        return entityManager.createQuery(jpql, User.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    @Override
    public Optional <User> findByPhoneNumber (String phoneNumber) {
        String jpql = "SELECT u FROM User u  WHERE u.phoneNumber = :phoneNumber";
        return entityManager.createQuery(jpql, User.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    @Override
    public ResponseEntity <UserP> signInViaEmail (String email, String password) {
        Optional<User> user = this.findByEmail(email);
        if (user.isPresent())
            if (user.get().getPasswordHash() == password.hashCode())
                return new ResponseEntity<>(HttpStatusCode.valueOf(201));
            else return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        return new ResponseEntity<>(HttpStatusCode.valueOf(401));
    }
    
    @Override
    public ResponseEntity <UserP> signInViaPhoneNumber (String phoneNumber, String password) {
        Optional<User> user = this.findByPhoneNumber(phoneNumber);
        if (user.isPresent())
            if (user.get().getPasswordHash() == password.hashCode())
                return new ResponseEntity <>(HttpStatusCode.valueOf(201));
            else return new ResponseEntity <>(HttpStatusCode.valueOf(401));
        return new ResponseEntity<>(HttpStatusCode.valueOf(401));
    }
    
    @Override
    public ResponseEntity <UserP> signInViaUserName (String username, String password) {
        Optional<User> user = this.findByUserName(username);
        if (user.isPresent())
            if (user.get().getPasswordHash() == password.hashCode())
                return new ResponseEntity<>(HttpStatusCode.valueOf(201));
            else return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        return new ResponseEntity<>(HttpStatusCode.valueOf(401));
    }
}
