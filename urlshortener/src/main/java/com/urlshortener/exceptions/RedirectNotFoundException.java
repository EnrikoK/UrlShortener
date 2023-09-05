package com.urlshortener.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RedirectNotFoundException extends RuntimeException{
    public RedirectNotFoundException(String message) {
        super(message);
    }
}
