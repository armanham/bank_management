package com.bankuser.controller;

import com.bankuser.model.entity.Client;
import com.bankuser.model.proxy.UserP;
import com.bankuser.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import com.bankuser.model.proxy.*;

@RestController
@RequestMapping ( value = "/User" )
public class UserController {
    
    private UserService userService;
    
    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }
    
    public UserController () {}
    
    @PostMapping ( value = "/signup" )
    public @ResponseBody ResponseEntity <UserP> signUp (@Valid @RequestBody @NonNull UserP userP) {
        return userService.signup(userP);
    }
    
    @GetMapping ( value = "/signin" )
    public @ResponseBody ResponseEntity <UserP> signIn (@RequestBody @NonNull SignIn signIn) {
        return userService.signIn(signIn.getLogin(), signIn.getPassword(), userService.loginType(signIn.getLogin()));
    }
    
    @PutMapping ( value = "/editinfo" )
    public @ResponseBody ResponseEntity <UserP> editInfo (@Valid @RequestBody @NonNull UserP userP) {
        return userService.editInfo(userP);
    }
    
    @PutMapping ( value = "/changepassword" )
    public @ResponseBody ResponseEntity <UserP> changePassword (@RequestBody @NonNull PasswordChange passwordChange) {
        if (passwordChange.getNew1().equals(passwordChange.getNew2())) {
            return userService.passwordChange(passwordChange.getSignIn(), passwordChange.getNew1(), userService.loginType(passwordChange.getSignIn().getLogin()));
        } else {
            return new ResponseEntity <>(HttpStatusCode.valueOf(501));
        }
    }
    
    @PutMapping ( value = "/changeforgottedpassword" )
    public @ResponseBody ResponseEntity <UserP> changeForgottedPassword (@RequestBody @NonNull ForgottedPasswordChange forgottedPasswordChange) {
        if (forgottedPasswordChange.getNew1().equals(forgottedPasswordChange.getNew2())) {
            return userService.forgottedPasswordChange(forgottedPasswordChange.getLogin(), forgottedPasswordChange.getNew1(), userService.loginType(forgottedPasswordChange.getLogin()));
        } else {
            return new ResponseEntity <>(HttpStatusCode.valueOf(501));
        }
    }
    
    @GetMapping ( value = "/forgotpassword" )
    public @ResponseBody ResponseEntity <UserP> forgotPassword (@RequestBody @NonNull ForgotPassword forgotPassword) {
        return userService.forgotPassword(forgotPassword.getLogin(), userService.loginType(forgotPassword.getLogin()));
    }
    
    @PostMapping ( "/attachClientToUser" )
    public @ResponseBody ResponseEntity <User> attachClientToUser (@PathVariable long userId, @RequestBody Client client) {
        return ResponseEntity.ok().body(userService.addUserToTableClientToUser(userId, client));
    }
}