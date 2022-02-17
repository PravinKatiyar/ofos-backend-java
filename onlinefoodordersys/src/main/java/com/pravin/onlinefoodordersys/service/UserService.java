package com.pravin.onlinefoodordersys.service;

import java.util.List;

import com.pravin.onlinefoodordersys.exception.InsufficientBalanceexception;
import com.pravin.onlinefoodordersys.model.FoodItems;
import com.pravin.onlinefoodordersys.model.User;
import com.pravin.onlinefoodordersys.model.Userorder;

public interface UserService {
	  
	List<User> findAll();
	  
	User getUserByEmail(String email);

	boolean creditWallet(String userId, int addedbalance);

	//boolean debitWallet(Long userId, int orderprice);
	
	List<Userorder> getAllOrdersByCustomerId(String userId);
	
	void cancelOrder(String userId);

	Userorder addOrder(int itemQuantity, String customerId, String fooditemId);

	List<FoodItems> findFoodItemsbyRestaurant(String restid);

	List<FoodItems> findAllAvailableFoodItems();
	
	List<FoodItems> fetchFoodItemByRestaurantavailablefoodItems(String restid);

	Userorder viewOrderById(String orderId);

	boolean debitWallet(String userId, int orderprice, String restaurantId) throws InsufficientBalanceexception;

//	boolean creditWallet(Long userId, long orderId);
	
	
	
	
	
	
	
	 


}
