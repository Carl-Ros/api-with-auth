package com.carlros.secureapi.exception;

public class NonUniqueIdentifierException extends RuntimeException {
    public NonUniqueIdentifierException(String message){
        super(message);
    }
}
