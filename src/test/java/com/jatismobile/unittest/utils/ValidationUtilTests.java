package com.jatismobile.unittest.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

@ExtendWith(MockitoExtension.class)
public class ValidationUtilTests {

    @Mock
    Errors errors;

    @Test
    void constructor() throws Exception {
        ValidationUtil validationUtil = new ValidationUtil();
        Assertions.assertTrue(validationUtil instanceof ValidationUtil);
    }

    @Test
    void validationBodyRequest() {
        when(errors.hasErrors()).thenReturn(true);
        List<ObjectError> errorList = new ArrayList<>();
        errorList.add(new ObjectError("TEST", "TEST"));
        when(errors.getAllErrors()).thenReturn(errorList);
        assertThrows(RuntimeException.class, ()-> {
            ValidationUtil.validationBodyRequest(errors);
        });
    }
}
