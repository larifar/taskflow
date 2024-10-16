package com.taskflow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ControllerAdvice
public class ExceptionsController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ExceptionTaskFlow.class)
    public ResponseEntity<String> handleCustomException(ExceptionTaskFlow ex){
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.OK);
    }
}
