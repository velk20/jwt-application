package com.jwt.application.util.validation;


import com.jwt.application.entity.UserEntity;
import com.jwt.application.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber,String> {
    private final UserRepository userRepository;

    public UniquePhoneNumberValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<UserEntity> byPhone = userRepository.findByPhone(value);
        return byPhone.isEmpty();
    }

}
