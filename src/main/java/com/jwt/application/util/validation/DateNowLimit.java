package com.jwt.application.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DateNowLimitValidator.class)
public @interface DateNowLimit {
    String message() default "Invalid Year";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
