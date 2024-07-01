package com.mycompany.property_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException theBusinessException) {
        System.out.println("Business Exception thrown");
        return new ResponseEntity<List<ErrorModel>>(
                theBusinessException.getErrors(),
                HttpStatus.BAD_REQUEST
        );
    }
}
