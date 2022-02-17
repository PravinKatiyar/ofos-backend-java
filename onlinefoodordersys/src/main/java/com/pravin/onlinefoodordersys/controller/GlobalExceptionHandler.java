package com.pravin.onlinefoodordersys.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pravin.onlinefoodordersys.exception.CantAcceptCancelledOrderException;
import com.pravin.onlinefoodordersys.exception.CantConfirmCancelledOrderException;
import com.pravin.onlinefoodordersys.exception.CantConfirmRejectOrderException;
import com.pravin.onlinefoodordersys.exception.CantDeliverAgainOrderException;
import com.pravin.onlinefoodordersys.exception.CantRejectCancelledOrderException;
import com.pravin.onlinefoodordersys.exception.CantSendRejectedOrderException;
import com.pravin.onlinefoodordersys.exception.ContactNumberAlreadyExistException;
import com.pravin.onlinefoodordersys.exception.EmailAlreadyExistsException;
import com.pravin.onlinefoodordersys.exception.FoodItemNotFoundException;
import com.pravin.onlinefoodordersys.exception.InsufficientBalanceexception;
import com.pravin.onlinefoodordersys.exception.InvalidCustomerIdException;
import com.pravin.onlinefoodordersys.exception.InvalidItemQuantityException;
import com.pravin.onlinefoodordersys.exception.NegativeBalanceException;
import com.pravin.onlinefoodordersys.exception.OrderAlreadyCancelledException;
import com.pravin.onlinefoodordersys.exception.OrderAlreadyOutForDeliveryException;
import com.pravin.onlinefoodordersys.exception.OrderCancelException;
import com.pravin.onlinefoodordersys.exception.OrderMustBeAcceptedException;
import com.pravin.onlinefoodordersys.exception.RestaurantNotFoundException;
import com.pravin.onlinefoodordersys.exception.UnauthorizedException;
import com.pravin.onlinefoodordersys.exception.UserDoesNotExistException;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = CantAcceptCancelledOrderException.class)
	public ResponseEntity<String> CantAcceptCancelledOrderException(CantAcceptCancelledOrderException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = CantConfirmCancelledOrderException.class)
	public ResponseEntity<String> CantConfirmCancelledOrderException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = CantConfirmRejectOrderException.class)
	public ResponseEntity<String> CantConfirmRejectOrderException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = CantDeliverAgainOrderException.class)
	public ResponseEntity<String> CantDeliverAgainOrderException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = CantRejectCancelledOrderException.class)
	public ResponseEntity<String> CantRejectCancelledOrderException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);}
	
	@ExceptionHandler(value = CantSendRejectedOrderException.class)
	public ResponseEntity<String> CantSendRejectedOrderException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = ContactNumberAlreadyExistException.class)
	public ResponseEntity<String> ContactNumberAlreadyExistException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.ALREADY_REPORTED);
	}
	@ExceptionHandler(value = EmailAlreadyExistsException.class)
	public ResponseEntity<String> EmailAlreadyExistsException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.ALREADY_REPORTED);
	}
	@ExceptionHandler(value = FoodItemNotFoundException.class)
	public ResponseEntity<String> FoodItemNotFoundException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = InsufficientBalanceexception.class)
	public ResponseEntity<String> InsufficientBalanceexception(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = InvalidCustomerIdException.class)
	public ResponseEntity<String> InvalidCustomerIdException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = InvalidItemQuantityException.class)
	public ResponseEntity<String> InvalidItemQuantityException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = NegativeBalanceException.class)
	public ResponseEntity<String> NegativeBalanceException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = OrderAlreadyCancelledException.class)
	public ResponseEntity<String> OrderAlreadyCancelledException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = OrderAlreadyOutForDeliveryException.class)
	public ResponseEntity<String> OrderAlreadyOutForDeliveryException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = OrderCancelException.class)
	public ResponseEntity<String> OrderCancelException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = OrderMustBeAcceptedException.class)
	public ResponseEntity<String> OrderMustBeAcceptedException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = RestaurantNotFoundException.class)
	public ResponseEntity<String> RestaurantNotFoundException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = UnauthorizedException.class)
	public ResponseEntity<String> UnauthorizedException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = UserDoesNotExistException.class)
	public ResponseEntity<String> UserDoesNotExistException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
	