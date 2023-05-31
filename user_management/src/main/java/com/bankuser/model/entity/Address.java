package com.bankuser.model.entity;

import com.bankuser.model.proxy.AddressP;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    
    @Column ( name = "country", nullable = false, length = 50 )
    private String country;
    @Column ( name = "city", nullable = false, length = 50 )
    private String city;
    
    public Address () {
    }
    
    public Address (String country, String city) {
        this.country = country;
        this.city = city;
    }
    
    public Address (final Address address) {
        this.country = address.country;
        this.city = address.city;
    }
    
    public Address (final AddressP addressP) {
        this.city = addressP.getCity();
        this.country = addressP.getCountry();
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