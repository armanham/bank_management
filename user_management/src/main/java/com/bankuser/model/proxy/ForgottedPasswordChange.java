package com.bankuser.model.proxy;

public class ForgottedPasswordChange {
    
    private String login;
    private String new1;
    private String new2;
    
    public ForgottedPasswordChange () {}
    
    public ForgottedPasswordChange (String login, String new1, String new2) {
        this.login = login;
        this.new1 = new1;
        this.new2 = new2;
    }
    
    public String getLogin () {
        return login;
    }
    
    public void setLogin (String login) {
        this.login = login;
    }
    
    public String getNew1 () {
        return new1;
    }
    
    public void setNew1 (String new1) {
        this.new1 = new1;
    }
    
    public String getNew2 () {
        return new2;
    }
    
    public void setNew2 (String new2) {
        this.new2 = new2;
    }
}
