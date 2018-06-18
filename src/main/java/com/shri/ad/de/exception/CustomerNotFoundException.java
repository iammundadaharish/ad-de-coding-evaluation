package com.shri.ad.de.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shri.ad.de.vo.CollectorServiceConfiguration;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {
	public CustomerNotFoundException(String exception,CollectorServiceConfiguration config) {
		super(exception);
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