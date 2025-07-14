package com.example.controller;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletResponse;

import com.example.exception.AccountAlreadyExistsException;
import com.example.exception.MessageFormatException;
import com.example.exception.RegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
     @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleMessageBodyNotReadableExceptions(Exception e) {
        System.out.println("Invalid message body: " + e.getMessage());
        return ResponseEntity.badRequest().body("Invalid message body");
    }



    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<String> handleAccountAlreadyExistsExceptions(Exception e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body("The account already exists");
    }



    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleRegistrationExceptions(Exception e) {
        System.out.println(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }



   
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> handleLoginExceptions(Exception e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(e.getMessage());
    }



  
    @ExceptionHandler(MessageFormatException.class)
    public ResponseEntity<String> handleMessageFormatExceptions(Exception e) {
        System.out.println(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    
}
