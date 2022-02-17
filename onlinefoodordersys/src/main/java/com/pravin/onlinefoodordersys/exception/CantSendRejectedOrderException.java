package com.pravin.onlinefoodordersys.exception;


public class CantSendRejectedOrderException extends RuntimeException {

	public CantSendRejectedOrderException(String m) {
		 super(m);
	}
}
