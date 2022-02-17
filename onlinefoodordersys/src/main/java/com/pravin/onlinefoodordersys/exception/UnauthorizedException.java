package com.pravin.onlinefoodordersys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UnauthorizedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2336933678232929018L;

	public UnauthorizedException(String message) {
		super(message);
	}
}