package com.pravin.onlinefoodordersys.exception;

public class CantConfirmCancelledOrderException extends RuntimeException {

	public CantConfirmCancelledOrderException (String msg){
	 super(msg);
 }

}
