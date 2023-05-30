package com.bdg.cardholder_management_module.check.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull
@NotEmpty
@NotBlank
@ReportAsSingleViolation
public @interface NotNullEmptyBlankString {
    String message() default "Invalid string";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}