package com.pravin.onlinefoodordersys.exception;

public class InsufficientBalanceexception extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6573442479827077638L;

	public InsufficientBalanceexception(String msg) {
		super(msg);
	}

}
