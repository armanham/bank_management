package com.bankuser.validation;

import org.springframework.stereotype.Service;

@Service
public class Validation {
    
    private final String USERNAME_REGEX     = "^[a-zA-Z][a-zA-Z0-9_-]{2,19}$";
    private final String EMAIL_REGEX        = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private final String PASSWORD_REGEX     = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=.*[!-~])[A-Za-z\\d@#$%^&+=!-~]{8,20}$";
    private final String PHONE_NUMBER_REGEX = "\\+374\\d{8}";
    
    public boolean isValidUsername (String username) {
        return username.matches(USERNAME_REGEX);
    }
    
    public boolean isValidEmail (String email) {
        return email.matches(EMAIL_REGEX);
    }
    
    public boolean isValidPassword (String password) {
        return password.matches(PASSWORD_REGEX);
    }
    
    public boolean isValidPhoneNumber (String phone) {
        return phone.matches(PHONE_NUMBER_REGEX);
    }
}
