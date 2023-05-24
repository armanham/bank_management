package com.user_management.service;

import com.user_management.model.proxy.UserP;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<UserP> signup(final UserP userP);
    ResponseEntity<UserP> signIn(final String login, final String password, final int loginChoice);
    
}
