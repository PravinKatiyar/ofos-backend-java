package com.pravin.onlinefoodordersys.exception;

public class UserDoesNotExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 467820861966090283L;

	public UserDoesNotExistException(String msg){
		super(msg);
	}
	
}
