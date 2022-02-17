package com.pravin.onlinefoodordersys.exception;

public class RestaurantNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5782306916841987542L;
	public RestaurantNotFoundException(String message){
		super(message);
	}
}
