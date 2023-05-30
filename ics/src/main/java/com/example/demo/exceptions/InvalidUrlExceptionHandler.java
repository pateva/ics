package com.example.demo.exceptions;

import com.example.demo.controllers.dto.RecognitionResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidUrlExceptionHandler {

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<RecognitionResponseBody> handleException(Exception exception) {
        if(exception instanceof InvalidUrlException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
