package com.bankuser.model.proxy;

import com.bankuser.model.entity.Passport;
import com.bankuser.model.entity.UserEntity;
import jakarta.validation.constraints.Pattern;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserP {
    
    private String           firstName;
    private String           lastName;
    private Date             birthDate;
    private String           genderP;
    private AddressP         addressP;
    @Pattern ( regexp = "^[a-zA-Z0-9_.]{3,15}$", message = "Invalid Username Pattern" )
    private String           username;
    @Pattern ( regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$", message = "Invalid Email Pattern" )
    private String           email;
    @Pattern ( regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+])[a-zA-Z0-9!@#$%^&*()_+]{8,20}$", message = "Invalid Password Pattern" )
    private String           password;
    @Pattern ( regexp = "\\+374\\d{8}", message = "Invalid Phone Number Pattern" )
    private String           phoneNumber;
    private List <PassportP> passportPS;
    
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
        this.passportPS = castPassportPS(USER.getPassports());
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
    
    public String getGenderP () {
        return genderP;
    }
    
    public void setGenderP (String genderP) {
        this.genderP = genderP;
    }
    
    public AddressP getAddressP () {
        return addressP;
    }
    
    public void setAddressP (AddressP addressP) {
        this.addressP = addressP;
    }
    
    public List <PassportP> getPassportPS () {
        return passportPS;
    }
    
    public void setPassportPS (List <PassportP> passportPS) {
        this.passportPS = passportPS;
    }
    
    private List <PassportP> castPassportPS (final List <Passport> passports) {
        List <PassportP> passportsP = new ArrayList <>();
        passports.forEach(passport -> passportsP.add(new PassportP(passport)));
        return passportsP;
    }
}