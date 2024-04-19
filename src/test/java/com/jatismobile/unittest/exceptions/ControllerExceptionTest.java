package com.jatismobile.unittest.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jatismobile.unittest.dtos.ErrorResponseData;

@ExtendWith(MockitoExtension.class)
public class ControllerExceptionTest {
    ControllerException controllerException;

    @Mock
    HttpServletRequest httpServletRequest;

    @Test
    public void internalServerError() {
        controllerException = new ControllerException();
        ResponseEntity<Object> responseEntity = controllerException.internalServerError(httpServletRequest,
                new Exception("TEST"));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ErrorResponseData errorResponseData = (ErrorResponseData) responseEntity.getBody();
        assertEquals("Internal Server Error", errorResponseData.getTitle());
        assertEquals("TEST", errorResponseData.getDetail());
    }

    @Test
    public void badRequest() {
        controllerException = new ControllerException();
        ResponseEntity<Object> responseEntity = controllerException.badRequest(httpServletRequest,
                new RuntimeException("Name is required"));
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorResponseData errorResponseData = (ErrorResponseData) responseEntity.getBody();
        assertEquals("Bad Request", errorResponseData.getTitle());
        assertEquals("Name is required", errorResponseData.getDetail());
    }
}
