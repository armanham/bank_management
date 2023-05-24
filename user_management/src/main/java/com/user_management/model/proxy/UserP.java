package com.user_management.model.proxy;

import com.user_management.model.entity.Passport;
import com.user_management.model.entity.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserP {
    
    private String           firstName;
    private String           lastName;
    private Date             birthDate;
    private String           genderP;
    private AddressP         addressP;
    private String           userName;
    private String           email;
    private String           password;
    private String           phoneNumber;
    private List <PassportP> passportPS;
    
    public UserP () {}
    
    public UserP (final User USER) {
        this.firstName = USER.getFirstName();
        this.lastName = USER.getLastName();
        this.userName = USER.getUsername();
        this.addressP = new AddressP(USER.getAddress());
        this.email = USER.getEmail();
        this.birthDate = USER.getBirthDate();
        this.genderP = USER.getGender();
        this.passportPS = castToAddressP(USER.getPassports());
        this.password = USER.getPassword();
        this.phoneNumber = USER.getPhoneNumber();
    }
    
    public String getUserName () {
        return userName;
    }
    
    public void setUserName (String userName) {
        this.userName = userName;
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
    
    public List <PassportP> getPassports () {
        return passportPS;
    }
    
    public void setPassports (List <PassportP> passportPS) {
        this.passportPS = passportPS;
    }
    
    private List <PassportP> castToAddressP (final List <Passport> passports) {
        List <PassportP> passportP = new ArrayList <>();
        passports.forEach(passport -> passportP.add(new PassportP(passport)));
        return passportP;
    }
}