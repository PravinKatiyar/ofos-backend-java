package com.pravin.onlinefoodordersys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pravin.onlinefoodordersys.model.FoodItems;
import com.pravin.onlinefoodordersys.model.Restaurant;
import com.pravin.onlinefoodordersys.model.User;
import com.pravin.onlinefoodordersys.model.Userorder;

@Service
public interface AdminService {
	
	String restaurantName="[A-Z][a-z]{1,45}";
	String ownerName="[A-Z][a-z]{1,15}";
	String phoneNumber=("(0/91)?[7-9][0-9]{9}");
	String email="[a-zA-Z0-9+_.-]+(.+)$";
	String address="[A-Z][a-z]{1,45}";

	Restaurant getRestaurantDetails(String id);

	Restaurant createRestaurant(Restaurant restaurant);

	Restaurant updateRestaurant(Restaurant restaurant);

	void deleteRestaurant(String id);

	List<Restaurant> fetchAllRestaurants();

	User getUserByEmail(String name);

	//DeliveryGuy adddeliveryGuy(DeliveryGuy deliveryGuy);

	//void deleteDeliveryGuy(Long id);

//	List<DeliveryGuy> fetchAlldeliveryGuys();

	//void changeAvailabilityStatus(long id);
	
	Iterable<Userorder> listAllOrder();
	
	void acceptUserOrder(String userId);
	
	void rejectUserOrder(String userId);
	
	 Userorder viewOrderById(String id);
	 
	 List<Userorder> viewOrderByCustomerId(String userId);

	FoodItems getFoodItemsById(String itemId);

	List<FoodItems> getFoodItems();

	String deleteFoodItems(String itemId);

	FoodItems saveFoodItems(String restaurantId, FoodItems foodItems);

	FoodItems updateFoodItemsById(String itemId, FoodItems itemRequest);

	void orderOutForDelivery(String id);

	void deliveredOrder(String id);

}
