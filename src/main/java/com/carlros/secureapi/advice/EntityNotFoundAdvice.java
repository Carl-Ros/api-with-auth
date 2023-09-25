package com.carlros.secureapi.advice;

import com.carlros.secureapi.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EntityNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler
    public ResponseEntity<SimpleErrorResponse> EntityNotFoundHandler(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SimpleErrorResponse(e.getMessage()));
    }
}
