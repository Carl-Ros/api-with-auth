package com.carlros.secureapi.advice;

import com.carlros.secureapi.exception.NonUniqueIdentifierException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NonUniqueIdentifierAdvice {

    @ResponseBody
    @ExceptionHandler(NonUniqueIdentifierException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String NonUniqueIdentifierHandler(NonUniqueIdentifierException e){
        return e.getMessage();
    }
}
