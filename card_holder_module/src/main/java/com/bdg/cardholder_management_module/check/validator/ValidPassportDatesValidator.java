package com.bdg.cardholder_management_module.check.validator;

import com.bdg.cardholder_management_module.entity.PassportEntity;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidPassportDatesValidator implements ConstraintValidator<ValidPassportDates, PassportEntity> {

    @Override
    public void initialize(ValidPassportDates constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PassportEntity passport, ConstraintValidatorContext context) {
        LocalDate today = LocalDate.now();
        LocalDate givenDate = passport.getGivenDate().toLocalDate();
        LocalDate expireDate = passport.getExpireDate().toLocalDate();
        return !givenDate.isAfter(today) && expireDate.isAfter(today);
    }
}