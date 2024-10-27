package com.jwt.application.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateNowLimitValidator implements ConstraintValidator<DateNowLimit,Object> {
    @Override
    public void initialize(DateNowLimit constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Integer year = (Integer) value;
        return year <= LocalDate.now().getYear();
    }
}
