package com.carlros.secureapi.advice;

import com.carlros.secureapi.exception.NonUniqueIdentifierException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NonUniqueIdentifierAdvice {

    @ResponseBody
    @ExceptionHandler(NonUniqueIdentifierException.class)
    public ResponseEntity<SimpleErrorResponse> NonUniqueIdentifierHandler(NonUniqueIdentifierException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new SimpleErrorResponse(e.getMessage()));
    }
}
