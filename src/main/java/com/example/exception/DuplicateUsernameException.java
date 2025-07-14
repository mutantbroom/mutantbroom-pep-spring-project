package com.example.exception;

public class DuplicateUsernameException extends Exception {
    public DuplicateUsernameException(String error) {
        super(error);
    }   
    
}
