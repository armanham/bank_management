package com.bankuser.model.proxy;

import com.bankuser.model.entity.Address;

public class AddressP {
    private String country;
    private String city;
    
    public AddressP () {
    }
    
    public AddressP (String country, String city) {
        this.country = country;
        this.city = city;
    }

    public AddressP (final AddressP addressP) {
        this.country = addressP.country;
        this.city = addressP.city;
    }

    public AddressP (final Address address) {
        this.city = address.getCity();
        this.country = address.getCountry();
    }
    
    public String getCountry () {
        return country;
    }
    
    public void setCountry (String country) {
        this.country = country;
    }
    
    public String getCity () {
        return city;
    }
    
    public void setCity (String city) {
        this.city = city;
    }
}
