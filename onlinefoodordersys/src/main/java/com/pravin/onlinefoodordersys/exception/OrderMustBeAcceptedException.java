package com.pravin.onlinefoodordersys.exception;

public class OrderMustBeAcceptedException extends RuntimeException {

	public OrderMustBeAcceptedException(String msg) {
		super(msg);
	}
}
