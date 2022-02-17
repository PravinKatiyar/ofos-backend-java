package com.pravin.onlinefoodordersys.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pravin.onlinefoodordersys.exception.InvalidCustomerIdException;
import com.pravin.onlinefoodordersys.exception.RestaurantNotFoundException;
import com.pravin.onlinefoodordersys.model.FoodItems;
import com.pravin.onlinefoodordersys.model.Restaurant;
import com.pravin.onlinefoodordersys.model.User;
import com.pravin.onlinefoodordersys.model.Userorder;
import com.pravin.onlinefoodordersys.service.AdminService;
import com.pravin.onlinefoodordersys.service.UserService;

@RestController
@CrossOrigin(value = "*")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private AdminService service;

	// View List of all the users
	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@GetMapping(value = "")
	public String getdemotext() {
		return "This is online food ordering system application backend!!";
	}

	// View own profile
	@GetMapping(value = "/adminprofile")
	// The principal is the currently logged in user
	public ResponseEntity<User> getUser(Principal principal) {
		User user = service.getUserByEmail(principal.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/getRestaurantDetails/{restaurantId}")
	public Restaurant getRestaurantDetails(@PathVariable("restaurantId") String id) {

		if (service.getRestaurantDetails(id).equals(null)) {
			throw new RestaurantNotFoundException("Restaurant does not exist");
		} else {
			return service.getRestaurantDetails(id);
		}
	}

	// create the restaurant
	@PostMapping("/createRestaurant")
	public ResponseEntity<String> createEmployee(@RequestBody Restaurant restaurant) {
		Restaurant res = service.createRestaurant(restaurant);
		if (res == null) {
			throw new RestaurantNotFoundException("Restaurant not found");
		} else {
			ResponseEntity<String> responseEntity = new ResponseEntity<String>("Restaurant is added successfully!!",
					HttpStatus.OK);
			return responseEntity;
		}
	}

	// update the restaurant

	@PutMapping("/updateRestaurant")
	public ResponseEntity<String> updateRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant res = service.updateRestaurant(restaurant);
		if (res != null) {
			ResponseEntity<String> responseEntity = new ResponseEntity<String>(
					"Restaurant details were updated successfully!!" + "\nRestaurant ID : "
							+ restaurant.getRestaurantId() + "\nRestaurant Name: " + restaurant.getRestaurantName()
							+ "\nOwnerName : " + restaurant.getOwnerName() + "\nphoneNumber : "
							+ restaurant.getPhoneNumber() + "\nRestaurant email : " + restaurant.getEmail()
							+ "\n Restaurant address : " + restaurant.getAddress(),
					HttpStatus.OK);
			return responseEntity;
		} else {
			ResponseEntity<String> responseEntity = new ResponseEntity<String>(
					"Sorry, restaurant details were not updated", HttpStatus.OK);
			return responseEntity;
		}
	}

	// delete the restaurant
	@DeleteMapping("/deleteRestaurant/{id}")
	public void deleteRestaurant(@PathVariable("id") String id) {
		service.deleteRestaurant(id);
	}

	// fetch all restaurants
	@GetMapping("/fetchAllRestaurant")
	public List<Restaurant> fetchAllRestaurant() {
		List<Restaurant> list = service.fetchAllRestaurants();
		return list;
	}

	@GetMapping("/allOrders")
	public Iterable<Userorder> viewAllOrder() {
		Iterable<Userorder> orderList = service.listAllOrder();
		return orderList;
	}

	@GetMapping("/viewOrderByCustomerId/{customerId}")
	public List<Userorder> viewOrderByCustomerId(@PathVariable("customerId") String customerId)
			throws InvalidCustomerIdException {
		return service.viewOrderByCustomerId(customerId);
	}

	@GetMapping("/searchOrder/{orderId}")
	public ResponseEntity<Userorder> viewOrderById(@PathVariable("orderId") String id) {
		Userorder order = service.viewOrderById(id);

		return new ResponseEntity<Userorder>(order, HttpStatus.OK);
	}

	@PutMapping("/acceptOrder/{id}")
	public ResponseEntity<String> acceptUserOrder(@PathVariable String id) {
		service.acceptUserOrder(id);
		return new ResponseEntity<String>("Order Accepted", HttpStatus.OK);
	}

	@PutMapping("/rejctedOrder/{id}")
	public ResponseEntity<String> rejectUserOrder(@PathVariable String id) {
		service.rejectUserOrder(id);
		return new ResponseEntity<String>("Order Rejected", HttpStatus.OK);
	}

	@PutMapping("/orderOutForDelivery/{id}")
	public ResponseEntity<String> orderOutForDelivery(@PathVariable String id) {
		service.orderOutForDelivery(id);
		return new ResponseEntity<String>("Out for Delivery", HttpStatus.OK);
	}

	@PutMapping("/deliveredOrder/{id}")
	public ResponseEntity<String> deliveredOrder(@PathVariable String id) {
		service.deliveredOrder(id);
		return new ResponseEntity<String>("Order Delivery", HttpStatus.OK);
	}

	@GetMapping("/fooditems")
	public List<FoodItems> findAllFoodItems() {
		return service.getFoodItems();
	}

	@GetMapping("/fooditems/{itemId}")
	public FoodItems findById(@PathVariable String itemId) {
		return service.getFoodItemsById(itemId);
	}

	@PostMapping("/restaurantId/{restId}/addfoodItems")
	public FoodItems addFoodItems(@PathVariable String restId, @RequestBody FoodItems foodItems) {
		return service.saveFoodItems(restId, foodItems);
	}

	@PutMapping("/update/{itemId}")
	public FoodItems updateFoodItems(@PathVariable String itemId, @RequestBody FoodItems foodItems) {
		return service.updateFoodItemsById(itemId, foodItems);
	}

	@DeleteMapping("/delete/{itemId}")
	public String deleteFoodItems(@PathVariable String itemId) {
		return service.deleteFoodItems(itemId);
	}

}
