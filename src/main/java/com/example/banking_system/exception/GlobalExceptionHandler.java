package com.example.banking_system.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountExceptions.class)
    public ResponseEntity<ExceptionDetails> handleNotFoundUserIdException(AccountExceptions ex , WebRequest webRequest) {
        ExceptionDetails exceptionDetails=new ExceptionDetails(LocalDateTime.now(), ex.getMessage(),webRequest.getDescription(false),"Account Exception");
        return new ResponseEntity<>(exceptionDetails,HttpStatus.NOT_FOUND);
    }
}