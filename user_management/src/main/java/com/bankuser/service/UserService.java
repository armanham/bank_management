package com.bankuser.service;

import com.bankuser.model.entity.Client;
import com.bankuser.model.entity.User;
import com.bankuser.model.proxy.UserP;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<UserP> signup(final UserP userP);
    ResponseEntity<UserP> signIn(final String login, final String password, final int loginChoice);
    public User addUserToTableClientToUser(long id, Client client);
}
