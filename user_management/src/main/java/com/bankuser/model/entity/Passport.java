package com.bankuser.model.entity;

import com.bankuser.model.proxy.PassportP;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table ( name = "passport" )
public class Passport {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long       id;
    @ManyToOne
    @JoinColumn ( name = "user_entity" )
    private UserEntity userEntity;
    @Column ( name = "number", nullable = false, length = 15, unique = true )
    private String     number;
    @Column ( name = "issue", nullable = false )
    private Date       issue;
    @Column ( name = "expired", nullable = false )
    private Date       expired;
    @Column ( name = "authority", nullable = false, length = 50 )
    private String     authority;
    @Column ( name = "gender", nullable = false, length = 6 )
    private String     gender;
    @Column ( name = "flag", nullable = false )
    private Boolean    flag;
    
    public Passport () {}
    
    public Passport (UserEntity user, String number, Date issue, Date expired, String authority, String gender) {
        this.userEntity = user;
        this.number = number;
        this.issue = issue;
        this.expired = expired;
        this.authority = authority;
        this.gender = gender;
        this.flag = true;
    }
    
    public Passport (final Passport passport) {
        this.userEntity = passport.userEntity;
        this.number = passport.number;
        this.issue = passport.issue;
        this.expired = passport.expired;
        this.authority = passport.authority;
        this.gender = passport.gender;
        this.flag = passport.flag;
    }
    
    public Passport (final PassportP passportP) {
        this.number = passportP.getNumber();
        this.issue = passportP.getIssue();
        this.expired = passportP.getExpired();
        this.authority = passportP.getAuthority();
        this.gender = passportP.getGender();
        this.flag = true;
    }
    
    public void setId (Long id) {
        this.id = id;
    }
    
    public Long getId () {
        return id;
    }
    
    public UserEntity getUser () {
        return userEntity;
    }
    
    public void setUser (UserEntity user) {
        this.userEntity = user;
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