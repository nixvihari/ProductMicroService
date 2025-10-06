package com.example.app.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> productNotFoundExceptionHandler(ProductNotFoundException ex) {
		logger.error("PRODUCT SERVICE: Product not found exception : {}", ex.getMessage(), ex);
		
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<String> badRequestExceptionHandler(BadRequestException ex) {
		logger.error("PRODUCT SERVICE: Bad request exception: {}", ex.getMessage(), ex);
		
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> genericExceptionHandler(Exception ex) {
		logger.error("PRODUCT SERVICE: Exception: {}", ex.getMessage(), ex);
		
		return new ResponseEntity<>("Global Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
