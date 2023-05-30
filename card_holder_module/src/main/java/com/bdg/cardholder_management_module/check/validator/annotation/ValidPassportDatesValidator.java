package com.bdg.cardholder_management_module.check.validator.annotation;

import com.bdg.cardholder_management_module.model.request.create.PassportCreatingRequest;
import com.bdg.cardholder_management_module.model.request.update.PassportUpdateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidPassportDatesValidator implements ConstraintValidator<ValidPassportDates, Object> {

    @Override
    public void initialize(ValidPassportDates constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object passport, ConstraintValidatorContext context) {
        LocalDate today = LocalDate.now();

        if (passport instanceof PassportCreatingRequest passportCreatingRequest) {
            LocalDate givenDate = LocalDate.parse(passportCreatingRequest.givenDate());
            LocalDate expireDate = LocalDate.parse(passportCreatingRequest.expireDate());
            return !givenDate.isAfter(today) && expireDate.isAfter(today);
        } else if (passport instanceof PassportUpdateRequest passportUpdateRequest) {
            LocalDate givenDate = LocalDate.parse(passportUpdateRequest.givenDate());
            LocalDate expireDate = LocalDate.parse(passportUpdateRequest.expireDate());
            return !givenDate.isAfter(today) && expireDate.isAfter(today);
        }
        return false;
    }
}