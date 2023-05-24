package com.user_management.model.entity;

import com.user_management.model.proxy.PassportP;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table
public class Passport {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user", unique = true)
    private User user;
    @Column(name = "number", nullable = false, length = 15)
    private String number;
    @Column(name = "issue", nullable = false)
    private Date issue;
    @Column(name = "expired", nullable = false)
    private Date expired;
    @Column(name = "authority", nullable = false, length = 50)
    private String authority;
    @Column(name = "gender", nullable = false, length = 6)
    private String gender;

    public Passport(){}

    public Passport(User user, String number, Date issue, Date expired, String authority, String gender) {
        this.user = user;
        this.number = number;
        this.issue = issue;
        this.expired = expired;
        this.authority = authority;
        this.gender = gender;
    }

    public Passport(final Passport passport) {
        this.user = passport.user;
        this.number = passport.number;
        this.issue = passport.issue;
        this.expired = passport.expired;
        this.authority = passport.authority;
        this.gender = passport.gender;
    }

    public Passport(final PassportP passportP) {
        this.user = new User(passportP.getUser());
        this.number = passportP.getNumber();
        this.issue = passportP.getIssue();
        this.expired = passportP.getExpired();
        this.authority = passportP.getAuthority();
        this.gender = passportP.getGender();
    }

    public void setId (Long id) {
        this.id = id;
    }
    
    public Long getId () {
        return id;
    }
    
    public User getUser () {
        return user;
    }
    
    public void setUser (User user) {
        this.user = user;
    }
    
    public String getNumber () {
        return number;
    }
    
    public void setNumber (String number) {
        this.number = number;
    }
    
    public Date getIssue () {
        return issue;
    }
    
    public void setIssue (Date issue) {
        this.issue = issue;
    }
    
    public Date getExpired () {
        return expired;
    }
    
    public void setExpired (Date expired) {
        this.expired = expired;
    }
    
    public String getAuthority () {
        return authority;
    }
    
    public void setAuthority (String authority) {
        this.authority = authority;
    }
    
    public String getGender () {
        return gender;
    }
    
    public void setGender (String gender) {
        this.gender = gender;
    }
}
