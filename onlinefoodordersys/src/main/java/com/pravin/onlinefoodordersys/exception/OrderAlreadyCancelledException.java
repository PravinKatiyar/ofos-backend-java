package com.pravin.onlinefoodordersys.exception;


public class OrderAlreadyCancelledException extends RuntimeException {

	public OrderAlreadyCancelledException(String msg) {
		super(msg);
	}
}
