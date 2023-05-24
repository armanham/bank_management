package com.bdg.cardholder_management_module.entity;

import com.bdg.cardholder_management_module.model.PassportModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.sql.Date;

@Entity
@Table(name = "passport")
@Check(
        name = "check_giv_date_exp_date",
        constraints = "giv_date < exp_date"
)
@NoArgsConstructor
@Getter
@Setter
public class
PassportEntity {

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

    @Column(name = "created_on")
    @Temporal(TemporalType.DATE)
    private Date createdOn;

    @Column(name = "updated_on")
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    public PassportEntity(PassportModel passportModel){
        this.serialNumber = passportModel.getSerialNumber();
        this.nationality = passportModel.getNationality();
        this.givenDate = Date.valueOf(passportModel.getGivenDate());
        this.expireDate = Date.valueOf(passportModel.getExpireDate());
        this.givenBy = passportModel.getGivenBy();
    }

    public PassportEntity getFromModel(PassportModel passportModel){
        this.serialNumber = passportModel.getSerialNumber();
        this.nationality = passportModel.getNationality();
        this.givenDate = Date.valueOf(passportModel.getGivenDate());
        this.expireDate = Date.valueOf(passportModel.getExpireDate());
        this.givenBy = passportModel.getGivenBy();
        return this;
    }
}