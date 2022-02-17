package com.pravin.onlinefoodordersys.exception;

public class FoodItemNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3064504160978502558L;
	public FoodItemNotFoundException(String message){
		super(message);
	}

}
