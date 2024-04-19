package com.jatismobile.unittest.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jatismobile.unittest.dtos.ErrorResponseData;

@ControllerAdvice
public class ControllerException {
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> internalServerError(HttpServletRequest httpServletRequest,
            Exception exception) {
        ErrorResponseData errorResponseData = new ErrorResponseData();
        errorResponseData.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponseData.setTitle("Internal Server Error");
        errorResponseData.setDetail(exception.getMessage());
        return new ResponseEntity<>(errorResponseData, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> badRequest(HttpServletRequest httpServletRequest,
            Exception exception) {
        ErrorResponseData errorResponseData = new ErrorResponseData();
        errorResponseData.setCode(HttpStatus.BAD_REQUEST.value());
        errorResponseData.setTitle("Bad Request");
        errorResponseData.setDetail(exception.getMessage());
        return new ResponseEntity<>(errorResponseData, HttpStatus.BAD_REQUEST);
    }
}
