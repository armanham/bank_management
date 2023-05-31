package com.bankuser.service;

import com.bankuser.model.proxy.SignIn;
import com.bankuser.model.proxy.UserP;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    
    ResponseEntity <UserP> signup (final UserP userP);
    
    ResponseEntity <UserP> signIn (final String login, final String password, final int loginChoice);
    
    ResponseEntity <UserP> editInfo (final UserP userP);
    
    boolean contains (final String string, final char symbol);
    
    ResponseEntity <UserP> forgotPassword (final String login, final int loginChoice);
    
    int loginType (final String login);
    
    ResponseEntity <UserP> forgottedPasswordChange (final String login, final String password, final int loginChoice);
    
    ResponseEntity <UserP> passwordChange (SignIn signIn, String password, int loginChoice);
}