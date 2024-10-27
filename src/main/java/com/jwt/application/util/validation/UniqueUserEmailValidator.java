package com.jwt.application.util.validation;


import com.jwt.application.entity.UserEntity;
import com.jwt.application.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;


public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail,String> {
    private final UserRepository userRepository;


    public UniqueUserEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //! FIX THE EMAIL CHECKER IF THE USER IS CURRENT LOGGED USER
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(value);

        return userEntityOptional.isEmpty();
    }
}
