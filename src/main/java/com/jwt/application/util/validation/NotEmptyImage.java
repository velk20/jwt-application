package com.jwt.application.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyImageValidator.class)
public @interface NotEmptyImage {
    String message() default "Invalid image";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
