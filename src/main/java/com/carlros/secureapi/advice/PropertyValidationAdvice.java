package com.carlros.secureapi.advice;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PropertyValidationAdvice {

    @ResponseBody
    @ExceptionHandler
    ResponseEntity<SimpleErrorResponse> PropertyValidationHandler(ConstraintViolationException e){
        String[] errors = e.getConstraintViolations().stream()
            .map(err -> "'" +err.getPropertyPath() + "' " + err.getMessage())
                .toArray(String[]::new);

        return ResponseEntity.badRequest().body(new SimpleErrorResponse(errors));
    }
}
