package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(ClientException.class)
   public ResponseEntity<String> statusCode400Response(ClientException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
   }     
   
   @ExceptionHandler(UnauthorizedException.class)
   public ResponseEntity<String> statusCode401(UnauthorizedException ex){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
   }
    
   @ExceptionHandler(IllegalArgumentException.class)
   public ResponseEntity<String> illegalArgHandler(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
   }
}
