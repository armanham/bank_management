package com.bankuser.repository.imp;

import com.bankuser.model.entity.UserEntity;
import com.bankuser.model.proxy.UserP;
import com.bankuser.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl extends SimpleJpaRepository<UserEntity, Long> implements UserRepository {
    private final EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(final EntityManager entityManager) {
        super(UserEntity.class, entityManager);
        this.entityManager = entityManager;
    }

    
    @Override
    public Optional<UserEntity> findByPassportNumber(final String passportNumber) {
        String jpql = "SELECT u FROM UserEntity u inner join Passport p WHERE p.number = :passportNumber";
        return entityManager.createQuery(jpql, UserEntity.class)
                .setParameter("passportNumber", passportNumber)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    @Override
    public Optional <UserEntity> findByUsername (final String userName) {
        String jpql = "SELECT u FROM UserEntity u  WHERE u.username = :userName";
        return entityManager.createQuery(jpql, UserEntity.class)
                .setParameter("userName", userName)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    @Override
    public Optional <UserEntity> findByEmail (final String email) {
        String jpql = "SELECT u FROM UserEntity u  WHERE u.email = :email";
        return entityManager.createQuery(jpql, UserEntity.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    @Override
    public Optional <UserEntity> findByPhoneNumber (final String phoneNumber) {
        String jpql = "SELECT u FROM UserEntity u  WHERE u.phoneNumber = :phoneNumber";
        return entityManager.createQuery(jpql, UserEntity.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public ResponseEntity <UserP> signInViaEmail (final String email, final String password) {
        Optional<UserEntity> user = this.findByEmail(email);
        if (user.isPresent())
            if (user.get().getPasswordHash() == password.hashCode())
                return new ResponseEntity<>(HttpStatusCode.valueOf(201));
            else return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        return new ResponseEntity<>(HttpStatusCode.valueOf(401));
    }


    @Override
    public ResponseEntity <UserP> signInViaPhoneNumber (final String phoneNumber, final String password) {
        Optional<UserEntity> user = this.findByPhoneNumber(phoneNumber);
        if (user.isPresent())
            if (user.get().getPasswordHash() == password.hashCode())
                return new ResponseEntity <>(HttpStatusCode.valueOf(201));
            else return new ResponseEntity <>(HttpStatusCode.valueOf(401));
        return new ResponseEntity<>(HttpStatusCode.valueOf(401));
    }

    @Override
    public ResponseEntity <UserP> signInViaUserName (final String username, final String password) {
        Optional<UserEntity> user = this.findByUsername(username);
        if (user.isPresent())
            if (user.get().getPasswordHash() == password.hashCode())
                return new ResponseEntity<>(HttpStatusCode.valueOf(200));
            else return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        return new ResponseEntity<>(HttpStatusCode.valueOf(401));
    }
}
