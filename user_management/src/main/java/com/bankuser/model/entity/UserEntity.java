package com.bankuser.model.entity;

import com.bankuser.model.proxy.PassportP;
import com.bankuser.model.proxy.UserP;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@Entity
@Table ( name = "user_entity" )
public class UserEntity implements UserDetails {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private           Long            id;
    @Column ( name = "first_name", nullable = false, length = 50 )
    private           String          firstName;
    @Column ( name = "last_name", nullable = false, length = 50 )
    private           String          lastName;
    @Column ( name = "birth_date", nullable = false )
    private           Date            birthDate;
    @Column ( name = "gender", nullable = false, length = 6 )
    private           String          gender;
    @Embedded
    private           Address         address;
    @Column ( name = "user_name", nullable = false, length = 50, unique = true )
    private           String          username;
    @Column ( name = "email", nullable = false, length = 50, unique = true )
    private           String          email;
    private transient String          password;
    @Column ( name = "password_hash", nullable = false )
    private           Integer         passwordHash;
    @Column ( name = "phone_number", nullable = false, length = 12, unique = true )
    private           String          phoneNumber;
    @OneToMany ( mappedBy = "userEntity" )
    private           List <Passport> passports;
    @Column ( name = "flag", nullable = false )
    private           Boolean         flag;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Enumerated(EnumType.STRING)
    private Role role;
    
    public UserEntity () {}
    
    public UserEntity (final UserP userP) {
        this.gender = userP.getGenderP();
        this.address = new Address(userP.getAddressP());
        this.email = userP.getEmail();
        this.birthDate = userP.getBirthDate();
        this.firstName = userP.getFirstName();
        this.lastName = userP.getLastName();
        this.username = userP.getUsername();
        this.password = userP.getPassword();
        this.phoneNumber = userP.getPhoneNumber();
        this.passports = castListPassports(userP.getPassportPS());
        this.flag = true;
        this.passwordHash = this.password.hashCode();
    }

    public UserEntity(Long id, String firstName, String lastName, Date birthDate, String gender, Address address, String username, String email, String password, Integer passwordHash, String phoneNumber, List<Passport> passports, Boolean flag, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.passports = passports;
        this.flag = flag;
        this.role = role;
    }


    public Long getId () {
        return id;
    }
    
    public void setId (Long id) {
        this.id = id;
    }
    
    public String getFirstName () {
        return firstName;
    }
    
    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName () {
        return lastName;
    }
    
    public void setLastName (String lastName) {
        this.lastName = lastName;
    }
    
    public Date getBirthDate () {
        return birthDate;
    }
    
    public void setBirthDate (Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getGender () {
        return gender;
    }
    
    public void setGender (String gender) {
        this.gender = gender;
    }
    
    public Address getAddress () {
        return address;
    }
    
    public void setAddress (Address address) {
        this.address = address;
    }
    
    public String getUsername () {
        return username;
    }
    
    public void setUsername (String username) {
        this.username = username;
    }
    
    public String getEmail () {
        return email;
    }
    
    public void setEmail (String email) {
        this.email = email;
    }
    
    public String getPassword () {
        return password;
    }
    
    public void setPassword (String password) {
        this.password = password;
        this.setPasswordHash(password.hashCode());
    }
    
    public Integer getPasswordHash () {
        return passwordHash;
    }
    
    public void setPasswordHash (Integer passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    public String getPhoneNumber () {
        return phoneNumber;
    }
    
    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Boolean getFlag () {
        return flag;
    }
    
    public void setFlag (Boolean flag) {
        this.flag = flag;
    }
    
    public List <Passport> getPassports () {
        return passports;
    }
    
    public void setPassports (List <Passport> passports) {
        this.passports = passports;
    }
    
    private List <Passport> castListPassports (final List <PassportP> passportPS) {
        List <Passport> passports = new ArrayList <>();
        passportPS.forEach(passportP -> passports.add(new Passport(passportP)));
        return passports;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}