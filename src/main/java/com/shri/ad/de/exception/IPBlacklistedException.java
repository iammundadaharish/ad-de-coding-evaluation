package com.shri.ad.de.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shri.ad.de.vo.CollectorServiceConfiguration;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IPBlacklistedException extends RuntimeException {   

    public IPBlacklistedException(String message,CollectorServiceConfiguration config){
        super(message);
        this.config = config;
    }   
    
    private CollectorServiceConfiguration config;

	public CollectorServiceConfiguration getConfig() {
		return config;
	}

	public void setConfig(CollectorServiceConfiguration config) {
		this.config = config;
	}
}
