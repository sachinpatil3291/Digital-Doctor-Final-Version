package com.sr.digidoc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sr.digidoc.model.ExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex){
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorMsg(ex.getMessage());
		response.setStatusCode(1);
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
