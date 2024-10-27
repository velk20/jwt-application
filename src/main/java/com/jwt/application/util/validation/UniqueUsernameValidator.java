package com.jwt.application.util.validation;


import com.jwt.application.entity.UserEntity;
import com.jwt.application.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {
    private final UserRepository userRepository;

    public UniqueUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<UserEntity> userEntityOptional = userRepository.getUserByUsername(value);

        return userEntityOptional.isEmpty();
    }
}
