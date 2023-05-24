package com.bankuser.controller;

import com.bankuser.model.proxy.UserP;
import com.bankuser.service.UserService;
import com.bankuser.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/User")
public class UserController {
    private       UserService userService;
    private Validation validation;
    @Autowired
    public UserController (UserService userService, Validation validation) {
        this.userService = userService;
        this.validation = validation;
    }

    public UserController (){}
    
    @PutMapping (value = "/signup")
    public ResponseEntity <UserP> signUp(UserP userP) {
        if (validation.isValidUsername(userP.getUserName()) && validation.isValidEmail(userP.getEmail()) && validation.isValidPassword(userP.getPassword())){
            return userService.signup(userP);
        }
        return new ResponseEntity<UserP>(HttpStatusCode.valueOf(501));
    }
    
    @PostMapping (value = "/signin")
    public ResponseEntity <UserP> signIn(String login, String password) {
        if (validation.isValidUsername(login) && validation.isValidPassword(password)) {
            return userService.signIn(login, password, 1);
        }
        if (validation.isValidEmail(login) && validation.isValidPassword(password)) {
            return userService.signIn(login, password, 2);
        }
        if (validation.isValidPhoneNumber(login) && validation.isValidPassword(password)) {
            return userService.signIn(login, password, 3);
        }
        return new ResponseEntity<UserP>(HttpStatusCode.valueOf(501));
    }
    
    @PostMapping (value = "/editinfo")
    public ResponseEntity <UserP> editInfo(UserP userP) {
        
        return new ResponseEntity<UserP>(HttpStatusCode.valueOf(201));
    }
    
    @PostMapping (value = "/changepassword")
    public ResponseEntity <UserP> changePassword(UserP userP) {
        
        return new ResponseEntity<UserP>(HttpStatusCode.valueOf(201));
    }
    
    @PostMapping (value = "/changeforgottedpassword")
    public ResponseEntity <UserP> changeForgottedPassword(UserP userP) {
        
        return new ResponseEntity<UserP>(HttpStatusCode.valueOf(201));
    }
    
    @PostMapping (value = "/forgotpassword")
    public ResponseEntity <UserP> forgotPassword(UserP userP) {
        
        return new ResponseEntity<UserP>(HttpStatusCode.valueOf(201));
    }
}
