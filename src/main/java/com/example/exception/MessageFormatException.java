package com.example.exception;

public class MessageFormatException extends RuntimeException {
    public MessageFormatException(String message) {
        super("Message error: " + message);
    }
    
}
