package com.bankuser.repository;

import com.bankuser.model.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository <Passport, Long> {

}