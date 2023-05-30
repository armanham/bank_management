package com.bdg.cardholder_management_module.model.entity;

import com.bdg.cardholder_management_module.model.dto.PersonalInfoModel;
import com.bdg.cardholder_management_module.model.entity.demo.AccountDemoEntity;
import com.bdg.cardholder_management_module.model.entity.demo.CardDemoEntity;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
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
public class CardHolderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "f_name", nullable = false, length = 64)
    private String firstName;

    @Column(name = "l_name", nullable = false, length = 64)
    private String lastName;

    @Column(name = "dob", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name = "phone", nullable = false, length = 32)
    private String phone;

    @Column(name = "email", nullable = false, length = 32)
    private String email;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardHolderType cardHolderType;

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

    public CardHolderEntity() {
    }

    public void setPersonalInfo(final PersonalInfoModel personalInfo) {
        if (personalInfo.getFirstName() != null) {
            setFirstName(personalInfo.getFirstName());
        }
        if (personalInfo.getLastName() != null) {
            setLastName(personalInfo.getLastName());
        }
        if (personalInfo.getDob() != null) {
            setDob(Date.valueOf(personalInfo.getDob()));
        }
        if (personalInfo.getPhone() != null) {
            setPhone(personalInfo.getPhone());
        }
        if (personalInfo.getEmail() != null) {
            setEmail(personalInfo.getEmail());
        }
        if (personalInfo.getCardHolderType() != null) {
            setCardHolderType(personalInfo.getCardHolderType());
        }
        this.setUpdatedOn(Date.valueOf(LocalDate.now())); //TODO 000000000000000000000000000000000000000000000
    }

    public void initializePersonalInfo(PersonalInfoModel personalInfo) {
        setFirstName(personalInfo.getFirstName());
        setLastName(personalInfo.getLastName());
        setDob(Date.valueOf(personalInfo.getDob()));
        setPhone(personalInfo.getPhone());
        setEmail(personalInfo.getEmail());
        setCardHolderType(personalInfo.getCardHolderType());
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
        this.setUpdatedOn(Date.valueOf(LocalDate.now()));
    }

    public void removeAddress(AddressEntity address) {
        this.addresses.remove(address);
        address.getCardHolders().remove(this);
        this.setUpdatedOn(Date.valueOf(LocalDate.now()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CardHolderType getCardHolderType() {
        return cardHolderType;
    }

    public void setCardHolderType(CardHolderType cardHolderType) {
        this.cardHolderType = cardHolderType;
    }

    public PassportEntity getPassport() {
        return passport;
    }

    public void setPassport(PassportEntity passport) {
        this.passport = passport;
    }

    public Set<AccountDemoEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountDemoEntity> accounts) {
        this.accounts = accounts;
    }

    public Set<CardDemoEntity> getCards() {
        return cards;
    }

    public void setCards(Set<CardDemoEntity> cards) {
        this.cards = cards;
    }

    public Set<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressEntity> addresses) {
        this.addresses = addresses;
    }
}