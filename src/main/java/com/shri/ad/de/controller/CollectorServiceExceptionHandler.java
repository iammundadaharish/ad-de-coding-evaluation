package com.shri.ad.de.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shri.ad.de.exception.CustomerNotFoundException;
import com.shri.ad.de.exception.IPBlacklistedException;
import com.shri.ad.de.exception.MalformedJsonException;
import com.shri.ad.de.exception.UnprocessableEntityException;
import com.shri.ad.de.exception.UserAgentBlacklistedException;
import com.shri.ad.de.vo.ErrorDetails;

@ControllerAdvice
@RestController
public class CollectorServiceExceptionHandler extends ResponseEntityExceptionHandler {		
	
	@ExceptionHandler(CustomerNotFoundException.class)
	  public final ResponseEntity<ErrorDetails> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	  }
	
	@ExceptionHandler(MalformedJsonException.class)
	  public final ResponseEntity<ErrorDetails> malformedException(MalformedJsonException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	  }
	
	@ExceptionHandler(UnprocessableEntityException.class)
	  public final ResponseEntity<ErrorDetails> unprocessableEntityException(UnprocessableEntityException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	  }
	
	@ExceptionHandler(UserAgentBlacklistedException.class)
	  public final ResponseEntity<ErrorDetails> userAgentBlacklistedException(UserAgentBlacklistedException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	  }
	@ExceptionHandler(IPBlacklistedException.class)
	  public final ResponseEntity<ErrorDetails> ipBlacklistedException(IPBlacklistedException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	  }
}