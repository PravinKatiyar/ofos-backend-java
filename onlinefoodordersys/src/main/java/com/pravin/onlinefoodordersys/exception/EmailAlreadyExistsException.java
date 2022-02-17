package com.pravin.onlinefoodordersys.exception;

public class EmailAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5195326773785946519L;
	
	public EmailAlreadyExistsException(String msg) {
		super(msg);
	}

}
