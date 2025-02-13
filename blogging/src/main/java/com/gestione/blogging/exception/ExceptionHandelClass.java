package com.gestione.blogging.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandelClass {

	@ExceptionHandler (value = EntityNotFoundException.class)
	protected ResponseEntity<Error> entityNotFound (EntityNotFoundException ex) {
		Error err = new Error();
		err.setMessage("Entity not found");
		err.setDetails(ex.getMessage());
		err.setStatus("404");
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = EntityExistsException.class)
	protected ResponseEntity<Error> alreadyExistsException(EntityExistsException ex) {
		Error err = new Error();
		err.setMessage("Entity already exists");
		err.setDetails(ex.getMessage());
		err.setStatus("409");
		return new ResponseEntity<>(err, HttpStatus.CONFLICT);
	}
}
