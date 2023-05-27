package com.example.demo.exceptions;

import com.example.demo.controllers.dto.RecognitionResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidRequestBodyExceptionHandler {

    @ExceptionHandler(InvalidRequestBodyException.class)
    public ResponseEntity<RecognitionResponseBody> handleException(Exception exception) {
//        if(exception instanceof InvalidRequestBodyException) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
