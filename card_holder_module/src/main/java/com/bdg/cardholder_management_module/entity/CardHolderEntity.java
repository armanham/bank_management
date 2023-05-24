package com.bdg.cardholder_management_module.entity;

import com.bdg.cardholder_management_module.entity.demo.AccountDemoEntity;
import com.bdg.cardholder_management_module.entity.demo.CardDemoEntity;
import com.bdg.cardholder_management_module.model.CardHolderModel;
import com.bdg.cardholder_management_module.model.PassportModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(
        name = "card_holder",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UC_phone",
                        columnNames = {"phone"}
                ),
                @UniqueConstraint(
                        name = "UC_email",
                        columnNames = {"email"}
                ),
                @UniqueConstraint(
                        name = "UC_passport",
                        columnNames = {"passport"}
                )

        }
)
@NoArgsConstructor
@Getter
@Setter
public class CardHolderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "f_name", nullable = false, length = 64)
    private String firstName;

    @Column(name = "l_name", nullable = false, length = 64)
    private String lastName;

    @Column(name = "dob", nullable = false, updatable = false)
    private Date dob;

    @Column(name = "phone", nullable = false, length = 32)
    private String phone;

    @Column(name = "email", nullable = false, length = 32)
    private String email;

    @Column(name = "type", nullable = false)
    private CardHolderType cardHolderType;

    @Column(name = "created_on")
    @Temporal(TemporalType.DATE)
    private Date createdOn;

    @Column(name = "updated_on")
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @OneToOne
    @JoinColumn(
            name = "passport",
            referencedColumnName = "serial_no",
            foreignKey = @ForeignKey(name = "FK_card_holder_passport_serial_no"),
            nullable = false
    )
    private PassportEntity passport;

    @OneToMany(mappedBy = "cardHolder")
    private Set<AccountDemoEntity> accounts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cardHolder")
    private Set<CardDemoEntity> cards = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "card_holder_address",
            joinColumns = {
                    @JoinColumn(
                            name = "card_holder_id",
                            foreignKey = @ForeignKey(name = "FK_card_holder_address_card_holder_id")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "address_id",
                            foreignKey = @ForeignKey(name = "FK_card_holder_address_address_id")
                    )
            }
    )
    private Set<AddressEntity> addresses = new LinkedHashSet<>();

    public CardHolderEntity(CardHolderModel cardHolderModel){
        this.firstName = cardHolderModel.getFirstName();
        this.lastName = cardHolderModel.getLastName();
        this.dob = Date.valueOf(cardHolderModel.getDob());
        this.phone = cardHolderModel.getPhone();
        this.email = cardHolderModel.getEmail();
        this.cardHolderType = cardHolderModel.getCardHolderType();
        //this.passport = new PassportEntity(passportModel);
    }

    public CardHolderEntity getFromModel(CardHolderModel cardHolderModel, PassportModel passportModel){
        this.firstName = cardHolderModel.getFirstName();
        this.lastName = cardHolderModel.getLastName();
        this.dob = Date.valueOf(cardHolderModel.getDob());
        this.phone = cardHolderModel.getPhone();
        this.email = cardHolderModel.getEmail();
        this.cardHolderType = cardHolderModel.getCardHolderType();
        this.passport = passport.getFromModel(passportModel);
        return this;
    }

    public CardHolderEntity getFromModel(CardHolderModel cardHolderModel){
        this.firstName = cardHolderModel.getFirstName();
        this.lastName = cardHolderModel.getLastName();
        this.dob = Date.valueOf(cardHolderModel.getDob());
        this.phone = cardHolderModel.getPhone();
        this.email = cardHolderModel.getEmail();
        this.cardHolderType = cardHolderModel.getCardHolderType();
        return this;
    }

    public void addAccount(AccountDemoEntity account) {
        this.accounts.add(account);
    }

    public void removeAccount(AccountDemoEntity account) {
        this.accounts.remove(account);
    }

    public void addCard(CardDemoEntity card) {
        this.cards.add(card);
    }

    public void removeCard(CardDemoEntity card) {
        this.cards.add(card);
    }

    public void addAddress(AddressEntity address) {
        this.addresses.add(address);
        address.getCardHolders().add(this);
    }

    public void removeAddress(AddressEntity address) {
        this.addresses.remove(address);
        address.getCardHolders().remove(this);
    }
}