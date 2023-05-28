package com.bankuser.model.proxy;

import com.bankuser.model.entity.Passport;
import com.bankuser.model.entity.UserEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserP {
    
    private String           firstName;
    private String           lastName;
    private Date             birthDate;
    private String           genderP;
    private AddressP         addressP;
    private String           username;
    private String           email;
    private String           password;
    private String           phoneNumber;
    
    public UserP () {}
    
    public UserP (final UserEntity USER) {
        this.firstName = USER.getFirstName();
        this.lastName = USER.getLastName();
        this.username = USER.getUsername();
        this.addressP = new AddressP(USER.getAddress());
        this.email = USER.getEmail();
        this.birthDate = USER.getBirthDate();
        this.genderP = USER.getGender();
        this.password = USER.getPassword();
        this.phoneNumber = USER.getPhoneNumber();
    }
    
    public String getUsername () {
        return username;
    }
    
    public void setUsername (String username) {
        this.username = username;
    }
    
    public String getFirstName () {
        return firstName;
    }
    
    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName () {
        return lastName;
    }
    
    public void setLastName (String lastName) {
        this.lastName = lastName;
    }
    
    public Date getBirthDate () {
        return birthDate;
    }
    
    public void setBirthDate (Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getGender () {
        return genderP;
    }
    
    public void setGender (String genderP) {
        this.genderP = genderP;
    }
    
    public AddressP getAddress () {
        return addressP;
    }
    
    public void setAddress (AddressP addressP) {
        this.addressP = addressP;
    }
    
    
    public String getEmail () {
        return email;
    }
    
    public void setEmail (String email) {
        this.email = email;
    }
    
    public String getPassword () {
        return password;
    }
    
    public void setPassword (String password) {
        this.password = password;
    }
    
    public String getPhoneNumber () {
        return phoneNumber;
    }
    
    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    private List <PassportP> castToAddressP (final List <Passport> passports) {
        List <PassportP> passportP = new ArrayList <>();
        passports.forEach(passport -> passportP.add(new PassportP(passport)));
        return passportP;
    }
}