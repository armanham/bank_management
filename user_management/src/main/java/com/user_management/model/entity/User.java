package com.user_management.model.entity;

import com.user_management.model.proxy.PassportP;
import com.user_management.model.proxy.UserP;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
public class User {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @Column(name = "gender", nullable = false, length = 6)
    private String gender;
    @Embedded
    private Address address;
    @Column(name = "user_name", nullable = false, length = 50, unique = true)
    private String username;
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;
    private transient String password;
    @Column(name = "password_hash", nullable = false)
    private Integer passwordHash;
    @Column(name = "phone_number", nullable = false, length = 12, unique = true)
    private String phoneNumber;
    @Column(name = "flag", nullable = false)
    private Boolean flag;
    @OneToMany(mappedBy = "user")
    private List<Passport> passports;

    public User(){}

    public User(final UserP userP) {
        
        this.gender = userP.getGender();
        this.address = new Address(userP.getAddress());
        this.email = userP.getEmail();
        this.birthDate = userP.getBirthDate();
        this.firstName = userP.getFirstName();
        this.lastName = userP.getLastName();
        this.passports = castListPassports(userP.getPassports());
        this.username = userP.getUserName();
        this.password = userP.getPassword();
        this.phoneNumber = userP.getPhoneNumber();
        this.flag = true;
        this.passwordHash = this.password.hashCode();
    }

    public Long getId () {
        return id;
    }
    
    public void setId (Long id) {
        this.id = id;
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
        return gender;
    }
    
    public void setGender (String gender) {
        this.gender = gender;
    }
    
    public Address getAddress () {
        return address;
    }
    
    public void setAddress (Address address) {
        this.address = address;
    }
    
    public String getUsername () {
        return username;
    }
    
    public void setUsername (String username) {
        this.username = username;
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
    
    public Integer getPasswordHash () {
        return passwordHash;
    }
    
    public void setPasswordHash (Integer passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    public String getPhoneNumber () {
        return phoneNumber;
    }
    
    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public List <Passport> getPassports () {
        return passports;
    }
    
    public void setPassports (List <Passport> passports) {
        this.passports = passports;
    }
    
    public Boolean getFlag () {
        return flag;
    }
    
    public void setFlag (Boolean flag) {
        this.flag = flag;
    }
    
    private List<Passport> castListPassports(final List<PassportP> passportPS) {
        List<Passport> passports = new ArrayList<>();
        passportPS.forEach(passportP -> passports.add(new Passport(passportP)));
        return passports;
    }
}
