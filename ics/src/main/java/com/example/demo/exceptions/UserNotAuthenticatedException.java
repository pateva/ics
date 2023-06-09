package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotAuthenticatedException extends RuntimeException{
   public  UserNotAuthenticatedException() {
        super("Authentication issue. Make sure you are using a valid API token");
    }
}
