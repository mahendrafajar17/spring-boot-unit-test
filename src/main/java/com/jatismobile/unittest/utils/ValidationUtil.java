package com.jatismobile.unittest.utils;

import java.util.Objects;

import org.springframework.validation.Errors;

public class ValidationUtil {

    public static void validationBodyRequest(Errors errors) {
        if (errors.hasErrors()) {
            throw new RuntimeException(Objects.requireNonNull(errors.getAllErrors().get(0).getDefaultMessage()));
        }
    }
}
