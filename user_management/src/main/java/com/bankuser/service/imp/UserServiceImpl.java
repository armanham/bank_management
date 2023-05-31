package com.bankuser.service.imp;

import com.bankuser.model.entity.*;
import com.bankuser.model.proxy.SignIn;
import com.bankuser.model.proxy.UserP;
import com.bankuser.repository.AndUserRepository;
import com.bankuser.repository.ClientRepository;
import com.bankuser.repository.ClientToUserRepository;
import com.bankuser.repository.UserRepository;
import com.bankuser.service.PassportService;
import com.bankuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    
    UserRepository         userRepository;
    AndUserRepository      andUserRepository;
    ClientRepository       clientRepository;
    ClientToUserRepository clientToUserRepository;
    PassportService        passportService;
    
    @Autowired
    public UserServiceImpl (UserRepository userRepository, AndUserRepository andUserRepository, ClientRepository clientRepository, ClientToUserRepository clientToUserRepository, PassportService passportService) {
        this.userRepository = userRepository;
        this.andUserRepository = andUserRepository;
        this.clientRepository = clientRepository;
        this.clientToUserRepository = clientToUserRepository;
        this.passportService = passportService;
    }
    
    @Override
    public ResponseEntity <UserP> signup (final UserP userP) {
        UserEntity user = new UserEntity(userP);
        boolean    t    = false;
        for (int i = 0; i < userP.getPassportPS().size(); i++) {
            Optional <UserEntity> op1 = userRepository.findByPassportNumber(userP.getPassportPS().get(i).getNumber());
            t = t || op1.isPresent();
        }
        Optional <UserEntity> op2 = userRepository.findByEmail(userP.getEmail());
        Optional <UserEntity> op3 = userRepository.findByUsername(userP.getUsername());
        Optional <UserEntity> op4 = userRepository.findByPhoneNumber(userP.getPhoneNumber());
        if (t || op2.isPresent() || op3.isPresent() || op4.isPresent()) {
            return new ResponseEntity <>(HttpStatusCode.valueOf(505));
        }
        userRepository.save(user);
        for (Passport i : user.getPassports()) {
            i.setUser(user);
        }
        passportService.save(user.getPassports());
        return new ResponseEntity <>(HttpStatusCode.valueOf(201));
    }
    
    @Override
    public ResponseEntity <UserP> signIn (final String login, final String password, final int loginChoice) {
        return switch (loginChoice) {
            case 1 -> userRepository.signInViaEmail(login, password);
            case 2 -> userRepository.signInViaPhoneNumber(login, password);
            case 3 -> userRepository.signInViaUsername(login, password);
            default -> new ResponseEntity <>(HttpStatusCode.valueOf(501));
        };
    }
    
    @Override
    public ResponseEntity <UserP> editInfo (final UserP userP) {
        return this.userRepository.update(userP);
    }
    
    @Override
    public ResponseEntity <UserP> forgotPassword (String login, int loginChoice) {
        return switch (loginChoice) {
            case 1 -> userRepository.forgotPasswordViaEmail(login);
            case 2 -> userRepository.forgotPasswordViaPhoneNumber(login);
            case 3 -> userRepository.forgotPasswordViaUsername(login);
            default -> new ResponseEntity <>(HttpStatusCode.valueOf(501));
        };
    }
    
    @Override
    public ResponseEntity <UserP> forgottedPasswordChange (String login, String password, int loginChoice) {
        return switch (loginChoice) {
            case 1 -> userRepository.resetPasswordViaEmail(login, password);
            case 2 -> userRepository.resetPasswordViaPhoneNumber(login, password);
            case 3 -> userRepository.resetPasswordViaUsername(login, password);
            default -> new ResponseEntity <>(HttpStatusCode.valueOf(501));
        };
    }
    
    @Override
    public ResponseEntity <UserP> passwordChange (SignIn signIn, String password, int loginChoice) {
        int value = this.signIn(signIn.getLogin(), signIn.getPassword(), loginChoice).getStatusCode().value();
        if (value >= 200 && value < 300) {
            return switch (loginChoice) {
                case 1 -> userRepository.resetPasswordViaEmail(signIn.getLogin(), password);
                case 2 -> userRepository.resetPasswordViaPhoneNumber(signIn.getLogin(), password);
                case 3 -> userRepository.resetPasswordViaUsername(signIn.getLogin(), password);
                default -> new ResponseEntity <>(HttpStatusCode.valueOf(501));
            };
        } else if (value >= 400 && value < 500) {
            return new ResponseEntity <>(HttpStatusCode.valueOf(401));
        } else {
            return new ResponseEntity <>(HttpStatusCode.valueOf(501));
        }
    }
    
    @Override
    public boolean contains (final String string, final char symbol) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == symbol) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int loginType (String login) {
        if (this.contains(login, '@')) {
            return 1;
        } else if (this.contains(login, '+')) {
            return 2;
        } else {
            return 3;
        }
    }
    
    @Override
    public UserEntity addUserToTableClientToUser (long id, Client client) {
        if (andUserRepository.getUserById(id) != null) {
            clientRepository.save(client);
            clientToUserRepository.save(new ClientToUser(andUserRepository.getUserById(id), client));
            return andUserRepository.getUserById(id);
        }
        throw new IllegalArgumentException("User not found");
    }
}