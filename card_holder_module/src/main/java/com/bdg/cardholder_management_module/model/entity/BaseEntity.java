package com.bdg.cardholder_management_module.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.sql.Date;
import java.time.LocalDate;

@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_on", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdOn;

    @Column(name = "updated_on")
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

    @Column(name = "deleted_on")
    private Date deletedOn;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    public BaseEntity() {
        this.createdOn = Date.valueOf(LocalDate.now());
        this.isDeleted = false;
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

    public Date getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(Date deletedOn) {
        this.deletedOn = deletedOn;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        if (deleted) {
            deletedOn = Date.valueOf(LocalDate.now());
        } else {
            createdOn = Date.valueOf(LocalDate.now());
        }
        isDeleted = deleted;
    }
}