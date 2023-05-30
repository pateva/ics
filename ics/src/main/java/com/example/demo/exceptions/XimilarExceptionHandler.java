package com.example.demo.exceptions;

import com.example.demo.controllers.dto.RecognitionResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class XimilarExceptionHandler {

    @ExceptionHandler(XimilarException.class)
    public ResponseEntity<RecognitionResponseBody> handleException(Exception exception) {

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
