package com.pravin.onlinefoodordersys.exception;

public class CantDeliverAgainOrderException extends RuntimeException {

	public CantDeliverAgainOrderException(String msg) {
		 super(msg);
	}

}
