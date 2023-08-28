package com.leds.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException
//<--makes the subclass of RuntimeException.
{
    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
