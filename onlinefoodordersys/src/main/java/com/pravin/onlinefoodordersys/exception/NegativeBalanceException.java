package com.pravin.onlinefoodordersys.exception;

public class NegativeBalanceException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NegativeBalanceException(String msg){
		super(msg);
	}

}
