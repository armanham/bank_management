package com.bankuser.repository.imp;

import com.bankuser.model.entity.Address;
import com.bankuser.model.entity.UserEntity;
import com.bankuser.model.proxy.UserP;
import com.bankuser.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional ( readOnly = false )
public class UserRepositoryImpl extends SimpleJpaRepository <UserEntity, Long> implements UserRepository {
    
    @PersistenceContext
    private final EntityManager entityManager;
    
    @Autowired
    public UserRepositoryImpl (final EntityManager entityManager) {
        super(UserEntity.class, entityManager);
        this.entityManager = entityManager;
    }
    
    @Override
    public Optional <UserEntity> findByPassportNumber (final String passportNumber) {
        String jpql = "SELECT u FROM UserEntity u inner join Passport p WHERE p.number = :passportNumber";
        return entityManager.createQuery(jpql, UserEntity.class).setParameter("passportNumber", passportNumber).getResultList().stream().findFirst();
    }
    
    @Override
    public Optional <UserEntity> findByUsername (final String username) {
        String jpql = "SELECT u FROM UserEntity u  WHERE u.username = :username";
        return entityManager.createQuery(jpql, UserEntity.class).setParameter("username", username).getResultList().stream().findFirst();
    }
    
    @Override
    public Optional <UserEntity> findByEmail (final String email) {
        String jpql = "SELECT u FROM UserEntity u  WHERE u.email = :email";
        return entityManager.createQuery(jpql, UserEntity.class).setParameter("email", email).getResultList().stream().findFirst();
    }
    
    @Override
    public Optional <UserEntity> findByPhoneNumber (final String phoneNumber) {
        String jpql = "SELECT u FROM UserEntity u  WHERE u.phoneNumber = :phoneNumber";
        return entityManager.createQuery(jpql, UserEntity.class).setParameter("phoneNumber", phoneNumber).getResultList().stream().findFirst();
    }
    
    @Override
    public ResponseEntity <UserP> signInViaEmail (final String email, final String password) {
        Optional <UserEntity> user = this.findByEmail(email);
        if (user.isPresent()) {
            if (user.get().getPasswordHash() == password.hashCode()) {
                return new ResponseEntity <>(HttpStatusCode.valueOf(201));
            } else {
                return new ResponseEntity <>(HttpStatusCode.valueOf(401));
            }
        }
        return new ResponseEntity <>(HttpStatusCode.valueOf(403));
    }
    
    @Override
    public ResponseEntity <UserP> signInViaPhoneNumber (final String phoneNumber, final String password) {
        Optional <UserEntity> user = this.findByPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            if (user.get().getPasswordHash() == password.hashCode()) {
                return new ResponseEntity <>(HttpStatusCode.valueOf(201));
            } else {
                return new ResponseEntity <>(HttpStatusCode.valueOf(401));
            }
        }
        return new ResponseEntity <>(HttpStatusCode.valueOf(403));
    }
    
    @Override
    public ResponseEntity <UserP> signInViaUsername (final String username, final String password) {
        Optional <UserEntity> user = this.findByUsername(username);
        if (user.isPresent()) {
            if (user.get().getPasswordHash() == password.hashCode()) {
                return new ResponseEntity <>(HttpStatusCode.valueOf(200));
            } else {
                return new ResponseEntity <>(HttpStatusCode.valueOf(401));
            }
        }
        return new ResponseEntity <>(HttpStatusCode.valueOf(403));
    }
    
    @Override
    public ResponseEntity <UserP> forgotPasswordViaEmail (final String email) {
        Optional <UserEntity> user = this.findByEmail(email);
        if (user.isPresent()) {
            return new ResponseEntity <>(HttpStatusCode.valueOf(201));
        } else {
            return new ResponseEntity <>(HttpStatusCode.valueOf(401));
        }
    }
    
    @Override
    public ResponseEntity <UserP> forgotPasswordViaPhoneNumber (final String phoneNumber) {
        Optional <UserEntity> user = this.findByPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            return new ResponseEntity <>(HttpStatusCode.valueOf(201));
        } else {
            return new ResponseEntity <>(HttpStatusCode.valueOf(401));
        }
    }
    
    @Override
    public ResponseEntity <UserP> forgotPasswordViaUsername (final String username) {
        Optional <UserEntity> user = this.findByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity <>(HttpStatusCode.valueOf(200));
        } else {
            return new ResponseEntity <>(HttpStatusCode.valueOf(401));
        }
    }
    
    @Override
    public ResponseEntity <UserP> resetPasswordViaEmail (final String email, final String password) {
        Optional <UserEntity> user = this.findByEmail(email);
        return getUserPResponseEntity(password, user);
    }
    
    @Override
    public ResponseEntity <UserP> resetPasswordViaPhoneNumber (final String phoneNumber, final String password) {
        Optional <UserEntity> user = this.findByPhoneNumber(phoneNumber);
        return getUserPResponseEntity(password, user);
    }
    
    @Override
    public ResponseEntity <UserP> resetPasswordViaUsername (final String username, final String password) {
        Optional <UserEntity> user = this.findByUsername(username);
        return getUserPResponseEntity(password, user);
    }
    
    public ResponseEntity <UserP> update (final UserP userP) {
        Optional <UserEntity> user = this.findByPassportNumber(userP.getPassportPS().get(0).getNumber());
        if (user.isPresent()) {
            if (userP.getAddressP() != null) {
                entityManager.createQuery("UPDATE UserEntity u SET u.address = :newaddress WHERE u.username = :username").setParameter("newaddress", new Address(userP.getAddressP())).setParameter("username", user.get().getUsername()).executeUpdate();
            }
            if (userP.getEmail() != null) {
                entityManager.createQuery("UPDATE UserEntity u SET u.email = :newemail WHERE u.username = :username").setParameter("newemail", userP.getEmail()).setParameter("username", user.get().getUsername()).executeUpdate();
            }
            if (userP.getUsername() != null) {
                entityManager.createQuery("UPDATE UserEntity u SET u.username = :newusername WHERE u.username = :username").setParameter("newusername", userP.getUsername()).setParameter("username", user.get().getUsername()).executeUpdate();
            }
            if (userP.getPhoneNumber() != null) {
                entityManager.createQuery("UPDATE UserEntity u SET u.phoneNumber = :newphoneNumber WHERE u.username = :username").setParameter("newphoneNumber", userP.getPhoneNumber()).setParameter("username", user.get().getUsername()).executeUpdate();
            }
            return new ResponseEntity <>(HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity <>(HttpStatusCode.valueOf(501));
    }
    
    public ResponseEntity <UserP> getUserPResponseEntity (String password, Optional <UserEntity> user) {
        if (user.isPresent()) {
            entityManager.createQuery("UPDATE UserEntity u SET u.passwordHash = :newPasswordHash WHERE u.username = :username").setParameter("newPasswordHash", password.hashCode()).setParameter("username", user.get().getUsername()).executeUpdate();
            return new ResponseEntity <>(HttpStatusCode.valueOf(201));
        } else {
            return new ResponseEntity <>(HttpStatusCode.valueOf(401));
        }
    }
}