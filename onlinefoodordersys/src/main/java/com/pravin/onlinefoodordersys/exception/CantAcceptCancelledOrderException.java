package com.pravin.onlinefoodordersys.exception;


public class CantAcceptCancelledOrderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9143182537087549338L;

	public CantAcceptCancelledOrderException(String msg) {
		super(msg);
	}

}
