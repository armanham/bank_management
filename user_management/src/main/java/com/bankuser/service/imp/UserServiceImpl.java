package com.bankuser.service.imp;

import com.bankuser.model.entity.User;
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
    public UserServiceImpl (){}
    
    @Override
    public ResponseEntity<UserP> signup (final UserP userP) {
        User user1 = new User(userP);
        Optional<User> op1 = userRepository.findByPassportNumber(userP.getPassports().get(0).getNumber());
        Optional<User> op2 = userRepository.findByEmail(userP.getEmail());
        Optional<User> op3 = userRepository.findByUserName(userP.getUserName());
        Optional<User> op4 = userRepository.findByPhoneNumber(userP.getPhoneNumber());
        if (op1.isPresent() || op2.isPresent() || op3.isPresent() || op4.isPresent()) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(501));
        }
        User user = new User(userP);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }
    
    @Override
    public ResponseEntity <UserP> signIn (String logIn, String password, int loginChoice) {
        switch (loginChoice) {
            case 1:
                return userRepository.signInViaUserName(logIn, password);
            case 2:
                return userRepository.signInViaEmail(logIn, password);
            case 3:
                return (userRepository.signInViaPhoneNumber(logIn, password));
            default:
                return new ResponseEntity<>(HttpStatusCode.valueOf(501));
        }
    }
}
