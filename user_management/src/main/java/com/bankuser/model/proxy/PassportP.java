package com.bankuser.model.proxy;

import com.bankuser.model.entity.Passport;

import java.sql.Date;

public class PassportP {
    private UserP  userP;
    private String number;
    private Date issue;
    private Date expired;
    private String  authority;
    private String genderP;

    public PassportP (){}

    public PassportP (UserP userP, String number, Date issue, Date expired, String authority, String genderP) {
        this.userP = userP;
        this.number = number;
        this.issue = issue;
        this.expired = expired;
        this.authority = authority;
        this.genderP = genderP;
    }

    public PassportP (final PassportP passportP) {
        this.userP = passportP.userP;
        this.number = passportP.number;
        this.issue = passportP.issue;
        this.expired = passportP.expired;
        this.authority = passportP.authority;
        this.genderP = passportP.genderP;
    }

    public PassportP(final Passport passport) {
        this.authority = passport.getAuthority();
        this.expired = passport.getExpired();
        this.issue = passport.getIssue();
        this.number = passport.getNumber();
        this.userP = new UserP(passport.getUser());
        this.genderP = passport.getGender();
    }
    
    public UserP getUser () {
        return userP;
    }
    
    public void setUser (UserP userP) {
        this.userP = userP;
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
        return genderP;
    }
    
    public void setGender (String genderP) {
        this.genderP = genderP;
    }
}
