package com.bankuser.model.proxy;

public class PasswordChange {
    
    private SignIn signIn;
    private String new1;
    private String new2;
    
    public PasswordChange () {}
    
    public PasswordChange (SignIn signIn, String new1, String new2) {
        this.signIn = signIn;
        this.new1 = new1;
        this.new2 = new2;
    }
    
    public SignIn getSignIn () {
        return signIn;
    }
    
    public void setSignIn (SignIn signIn) {
        this.signIn = signIn;
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
