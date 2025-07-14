package com.example.exception;

public class RegistrationException extends RuntimeException{
     public RegistrationException(String message) {
        super("Registration error: " + message);
    }
    
}
