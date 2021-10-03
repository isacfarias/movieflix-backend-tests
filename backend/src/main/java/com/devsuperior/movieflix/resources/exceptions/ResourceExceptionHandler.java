package com.devsuperior.movieflix.resources.exceptions;

import com.devsuperior.movieflix.services.exceptions.DataBaseException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidatetionError> validate(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		
		ValidatetionError error = new ValidatetionError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError(e.getMessage());
		error.setMessage("Validation exception");
		error.setPath(request.getRequestURI());
		
		for (FieldError filed : e.getBindingResult().getFieldErrors()) {
			error.addError(filed.getField(), filed.getDefaultMessage());
		}
		
		
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError error = resultStandardError(e,
                                                  request,
                                                  status,
                                                  "Resource not found");
		
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError error = resultStandardError(e,
				                                  request,
				                                  status,
				                                  "Database exception");
		
		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError error = resultStandardError(e,
                                                  request,
                                                  status,
                                                  "Illegal Argument");
		
		return ResponseEntity.status(status).body(error);
	}

	private StandardError resultStandardError(Exception e, HttpServletRequest request, HttpStatus status, String msg) {
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError(msg);
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return error;
	}
	
	
	

}