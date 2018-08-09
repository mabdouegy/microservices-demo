package com.mohamedomar.codechallenge.microservices.users.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Allow the controller to return a 404 if a user is not found by simply
 * throwing this exception. The @ResponseStatus causes Spring MVC to return a
 * 404 instead of the usual 500.
 * 
 * @author Mohamed Omar
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DuplicateUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateUserException(String email) {
		super("email already exists: " + email);
	}
}
