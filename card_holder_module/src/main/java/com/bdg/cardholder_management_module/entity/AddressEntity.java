package com.bdg.cardholder_management_module.entity;

import com.bdg.cardholder_management_module.model.AddressModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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
@NoArgsConstructor
@Getter
@Setter
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

    public AddressEntity(AddressModel addressModel){
        this.city = addressModel.getCity();
        this.country = addressModel.getCountry();
        this.street = addressModel.getStreet();
    }

    public AddressEntity getFromModel(AddressModel addressModel){
        this.street = addressModel.getStreet();
        this.city = addressModel.getCity();
        this.country = addressModel.getCountry();
        return this;
    }
}