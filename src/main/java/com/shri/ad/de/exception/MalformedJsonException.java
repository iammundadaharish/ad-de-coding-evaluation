package com.shri.ad.de.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MalformedJsonException extends RuntimeException {
    public MalformedJsonException(String exception){
    	 super(exception);
    }
}
