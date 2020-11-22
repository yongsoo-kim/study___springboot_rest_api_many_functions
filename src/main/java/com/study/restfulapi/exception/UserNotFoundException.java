package com.study.restfulapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//HTTP Status codes.
//2XX -> OK
//4XX -> Client
//5XX -> Server
//리소스가 없을때는 NOT FOUND
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
