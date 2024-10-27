package com.jwt.application.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class NotEmptyImageValidator implements ConstraintValidator<NotEmptyImage, MultipartFile[]> {
    @Override
    public boolean isValid(MultipartFile[] value, ConstraintValidatorContext context) {
        for (MultipartFile multipartFile : value) {
            if (multipartFile == null || multipartFile.getOriginalFilename() == null || multipartFile.getOriginalFilename().isEmpty()) {
                return false;
            }
        }

        return true;
    }
}
