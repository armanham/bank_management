package com.bdg.cardholder_management_module.entity;

import com.bdg.cardholder_management_module.model.PassportModel;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "passport")
@Check(
        name = "check_giv_date_exp_date",
        constraints = "giv_date < exp_date"
)
public class PassportEntity extends BaseEntity {

    @Id
    @Column(name = "serial_no", length = 24, updatable = false)
    private String serialNumber;

    @Column(name = "nationality", nullable = false, length = 32)
    private String nationality;

    @Column(name = "giv_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date givenDate;

    @Column(name = "exp_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expireDate;

    @Column(name = "given_by", nullable = false, length = 12)
    private String givenBy;

    @OneToOne(mappedBy = "passport")
    private CardHolderEntity cardHolder;

    public PassportEntity() {
    }

    public PassportEntity(PassportModel passportModel) {
        this.serialNumber = passportModel.getSerialNumber();
        this.nationality = passportModel.getNationality();
        this.givenDate = Date.valueOf(passportModel.getGivenDate());
        this.expireDate = Date.valueOf(passportModel.getExpireDate());
        this.givenBy = passportModel.getGivenBy();
    }

    public PassportEntity getFromModel(PassportModel passportModel) {
        this.serialNumber = passportModel.getSerialNumber();
        this.nationality = passportModel.getNationality();
        this.givenDate = Date.valueOf(passportModel.getGivenDate());
        this.expireDate = Date.valueOf(passportModel.getExpireDate());
        this.givenBy = passportModel.getGivenBy();
        return this;
    }

    public void setPassportInfo(PassportModel passportModel) {
        if (passportModel.getNationality() != null) {
            setNationality(passportModel.getNationality());
        }
        if (passportModel.getGivenDate() != null) {
            setGivenDate(Date.valueOf(passportModel.getGivenDate()));
        }
        if (passportModel.getExpireDate() != null) {
            setExpireDate(Date.valueOf(passportModel.getExpireDate()));
        }
        if (passportModel.getGivenBy() != null) {
            setGivenBy(passportModel.getGivenBy());
        }
        this.setUpdatedOn(Date.valueOf(LocalDate.now()));
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getGivenBy() {
        return givenBy;
    }

    public void setGivenBy(String givenBy) {
        this.givenBy = givenBy;
    }
}