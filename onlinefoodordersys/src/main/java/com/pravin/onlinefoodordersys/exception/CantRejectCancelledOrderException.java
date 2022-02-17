package com.pravin.onlinefoodordersys.exception;

public class CantRejectCancelledOrderException extends RuntimeException {

	public CantRejectCancelledOrderException(String m) {
		super(m);
	}
}
