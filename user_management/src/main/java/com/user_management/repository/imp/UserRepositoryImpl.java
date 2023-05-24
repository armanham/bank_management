package com.user_management.repository.imp;

import jakarta.persistence.EntityManager;
import com.user_management.model.entity.User;
import com.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
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
    public Optional <User> findByPhone (String phoneNumber) {
        String jpql = "SELECT u FROM User u  WHERE u.phoneNumber = :phoneNumber";
        return entityManager.createQuery(jpql, User.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    @Override
    public Optional <User> signInViaEmail (String email, String password) {
        return Optional.empty();
    }
    
    @Override
    public Optional <User> signInViaPhoneNumber (String phoneNumber, String password) {
        return Optional.empty();
    }
    
    @Override
    public Optional <User> signInViaUserName (String UserName, String password) {
        return Optional.empty();
    }
    
    // Other custom repository methods...
}
