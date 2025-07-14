package com.example.exception;

public class AccountAlreadyExistsException extends RegistrationException {
    public AccountAlreadyExistsException(String message) {
        super(message);
    }
    
    
}
