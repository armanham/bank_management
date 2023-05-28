package com.bankuser.service.imp;

import com.bankuser.model.entity.UserEntity;
import com.bankuser.model.proxy.UserP;
import com.bankuser.repository.UserRepository;
import com.bankuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    @Autowired
    public UserServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public ResponseEntity<UserP> signup (final UserP userP) {
        UserEntity user = new UserEntity(userP);
        //Optional<UserEntity> op1 = userRepository.findByPassportNumber(userP.getPassports().get(0).getNumber());
        Optional<UserEntity> op2 = userRepository.findByEmail(userP.getEmail());
        Optional<UserEntity> op3 = userRepository.findByUsername(userP.getUsername());
        Optional<UserEntity> op4 = userRepository.findByPhoneNumber(userP.getPhoneNumber());
        if (//op1.isPresent() ||
                op2.isPresent() || op3.isPresent() || op4.isPresent()) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(505));
        }
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }
    
    @Override
    public ResponseEntity <UserP> signIn (final String logIn, final String password, final int loginChoice) {
        return switch (loginChoice) {
            case 1 -> userRepository.signInViaUserName(logIn, password);
            case 2 -> userRepository.signInViaEmail(logIn, password);
            case 3 -> userRepository.signInViaPhoneNumber(logIn, password);
            default -> new ResponseEntity<>(HttpStatusCode.valueOf(501));
        };
    }
}
