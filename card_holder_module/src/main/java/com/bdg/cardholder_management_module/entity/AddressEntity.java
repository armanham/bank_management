package com.bdg.cardholder_management_module.entity;

import com.bdg.cardholder_management_module.model.AddressModel;
import jakarta.persistence.*;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(
        name = "address",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UC_street_city_state",
                        columnNames = {"street", "city", "country"}
                )
        }
)
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street", nullable = false, length = 64)
    private String street;

    @Column(name = "city", nullable = false, length = 64)
    private String city;

    @Column(name = "country", nullable = false, length = 64)
    private String country;

    @Column(name = "created_on")
    @Temporal(TemporalType.DATE)
    private Date createdOn;

    @Column(name = "updated_on")
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToMany(mappedBy = "addresses")
    private Set<CardHolderEntity> cardHolders = new LinkedHashSet<>();

    public AddressEntity() {
    }

    public AddressEntity(AddressModel addressModel) {
        this.city = addressModel.getCity();
        this.country = addressModel.getCountry();
        this.street = addressModel.getStreet();
    }

    public AddressEntity getFromModel(AddressModel addressModel) {
        this.street = addressModel.getStreet();
        this.city = addressModel.getCity();
        this.country = addressModel.getCountry();
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Set<CardHolderEntity> getCardHolders() {
        return cardHolders;
    }

    public void setCardHolders(Set<CardHolderEntity> cardHolders) {
        this.cardHolders = cardHolders;
    }
}