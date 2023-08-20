package com.example.WebAppProject.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;




@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value=FileNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleFileNotFoundException(FileNotFoundException exception) {    
		ErrorResponse errorResponse = new ErrorResponse();     
		errorResponse.setErrorMessage(exception.getMessage());   
		errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.toString());     
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); 
		}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
	public String handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
		return "File size limit exceeded. Please upload a file of smaller size.";
	}
	
	@ExceptionHandler(value= ConstraintViolationException.class)
	public ResponseEntity<ValidationErrorResponse> handleConstaintException(ConstraintViolationException ex) {
		ValidationErrorResponse errorResponse = new ValidationErrorResponse();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errorResponse.addError(violation.getMessage());
		}
		return ResponseEntity.badRequest().body(errorResponse);
	}
}
