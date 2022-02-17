package com.pravin.onlinefoodordersys.exception;

public class OrderCancelException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4034617567034479673L;
	
	public OrderCancelException(String msg){
		super(msg);
	}

}
