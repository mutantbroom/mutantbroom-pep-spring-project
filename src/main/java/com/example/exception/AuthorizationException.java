package com.example.exception;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String error) {
        super(error);
    }  
    
}
