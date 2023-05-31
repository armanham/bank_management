package com.bankuser.controller;

import com.bankuser.model.entity.Client;
import com.bankuser.model.entity.User;
import com.bankuser.model.proxy.AddressP;
import com.bankuser.model.proxy.PassportP;
import com.bankuser.model.proxy.UserP;
import com.bankuser.service.PassportService;
import com.bankuser.service.UserService;
import com.bankuser.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping (value = "/User")
public class UserController {
    private       UserService userService;
    
    private PassportService passportService;
    private Validation validation;
    @Autowired
    public UserController (UserService userService, Validation validation) {
        this.userService = userService;
        this.validation = validation;
    }

    public UserController (){}
    
    @PostMapping (value = "/signup")
    public ResponseEntity <UserP> signUp(@RequestBody UserP userP, @RequestBody PassportP passportP) {
        //if (validation.isValidUsername(userP.getUsername()) && validation.isValidEmail(userP.getEmail()) && validation.isValidPassword(userP.getPassword())){
            return userService.signup(userP);
        //}
        //return new ResponseEntity<UserP>(HttpStatusCode.valueOf(501));
    }
    
    @GetMapping (value = "/signup/Loka")
    public ResponseEntity <UserP> signUp() {
        UserP userP = new UserP();
        AddressP addressP = new AddressP("Armenia", "Yerevan");
        userP.setAddress(addressP);
        userP.setPassword("Lobzik");
        userP.setBirthDate(Date.valueOf("1999-02-02"));
        //if (validation.isValidUsername(userP.getUsername()) && validation.isValidEmail(userP.getEmail()) && validation.isValidPassword(userP.getPassword())){
        return userService.signup(userP);
        //}
        //return new ResponseEntity<UserP>(HttpStatusCode.valueOf(501));
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
    
    @GetMapping (value = "/hi")
    public ResponseEntity<UserP> hi(){
        AddressP addressP = new AddressP("Armenia", "Yerevan");
        PassportP passportP = new PassportP();
        UserP user = new UserP();
        user.setAddress(addressP);
        List<PassportP> list = new ArrayList <>();
        list.add(passportP);
        return new ResponseEntity <>(user, HttpStatusCode.valueOf(201));
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
    
    @PostMapping("/attachClientToUser")
    public ResponseEntity<User> attachClientToUser(@PathVariable long userId,@RequestBody Client client) {
    	return ResponseEntity.ok().body(userService.addUserToTableClientToUser(userId,client));
    }
}
