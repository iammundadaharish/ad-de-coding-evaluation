package com.shri.ad.de.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAgentBlacklistedException extends RuntimeException {

    public UserAgentBlacklistedException(){}

    public UserAgentBlacklistedException(String message){
        super(message);
    }

    public UserAgentBlacklistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAgentBlacklistedException(Throwable cause) {
        super(cause);
    }

    public UserAgentBlacklistedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
