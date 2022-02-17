package com.pravin.onlinefoodordersys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pravin.onlinefoodordersys.dao.FoodItemsRepository;
import com.pravin.onlinefoodordersys.dao.OrderRepository;
import com.pravin.onlinefoodordersys.dao.RestaurantRepository;
import com.pravin.onlinefoodordersys.dao.UserRepository;
import com.pravin.onlinefoodordersys.exception.CantAcceptCancelledOrderException;
import com.pravin.onlinefoodordersys.exception.CantConfirmCancelledOrderException;
import com.pravin.onlinefoodordersys.exception.CantConfirmRejectOrderException;
import com.pravin.onlinefoodordersys.exception.CantDeliverAgainOrderException;
import com.pravin.onlinefoodordersys.exception.CantRejectCancelledOrderException;
import com.pravin.onlinefoodordersys.exception.CantSendRejectedOrderException;
import com.pravin.onlinefoodordersys.exception.FoodItemNotFoundException;
import com.pravin.onlinefoodordersys.exception.OrderAlreadyCancelledException;
import com.pravin.onlinefoodordersys.exception.OrderAlreadyOutForDeliveryException;
import com.pravin.onlinefoodordersys.exception.OrderMustBeAcceptedException;
import com.pravin.onlinefoodordersys.exception.RestaurantNotFoundException;
import com.pravin.onlinefoodordersys.model.FoodItems;
import com.pravin.onlinefoodordersys.model.Restaurant;
import com.pravin.onlinefoodordersys.model.User;
import com.pravin.onlinefoodordersys.model.Userorder;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private FoodItemsRepository foodItemsRepository;

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmailIgnoreCase(email);
		return user;
	}

	@Override
	public Restaurant getRestaurantDetails(String id) {
		boolean bool = restaurantRepository.existsById(id);
		if (bool = true) {
			return restaurantRepository.findByRestaurantId(id);
		}
		throw new RestaurantNotFoundException("Restaurant Not Found");
	}

	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		restaurant.setCashcounter(0);
		return restaurantRepository.save(restaurant);
	}

	public Restaurant updateRestaurant(Restaurant restaurant) {
		boolean bool = restaurantRepository.existsById(restaurant.getRestaurantId());
		if (bool == true) {
			restaurantRepository.save(restaurant);
			return restaurant;
		} else {
			restaurantRepository.save(restaurant);
			return restaurant;
		}
	}

	@Override
	public void deleteRestaurant(String id) {
		restaurantRepository.deleteById(id);

	}

	@Override
	public List<Restaurant> fetchAllRestaurants() {
		return restaurantRepository.findAll();
	}

	@Override
	public Iterable<Userorder> listAllOrder() {
		Iterable<Userorder> list = orderRepository.viewAllnonCancelledOrders();
		return list;
	}

	@Override
	public List<Userorder> viewOrderByCustomerId(String userId) {
		String user = userRepository.findUserById(userId).getId();
		List<Userorder> list = orderRepository.findByUserId(user);

		return list;
	}

	@Override
	public Userorder viewOrderById(String id) {
		Optional<Userorder> order = orderRepository.findById(id);

		return order.get();
	}

	@Override
	public void acceptUserOrder(String orderId) {
		Userorder order = orderRepository.getOne(orderId);

		if ("Order Cancelled".equalsIgnoreCase(order.getOrderStatus())) {
			throw new CantAcceptCancelledOrderException("You can't Accept already cancelled order!!");
		}

		order.setOrderStatus("Accepted");
		orderRepository.save(order);

	}

	private void refundOrderAmount(String userId, String orderId) {
		String restaurantId = orderRepository.findorderByorderId(orderId).getRestaurantId();
		float totalCost = (float) orderRepository.findorderByorderId(orderId).getTotalCost();

		Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId);
		restaurant.setCashcounter((long) (restaurant.getCashcounter() - totalCost));

		User one = userRepository.getOne(userId);
		one.setWalletBalance(one.getWalletBalance() + totalCost);

		restaurantRepository.save(restaurant);
		userRepository.save(one);
	}

	@Override
	public void rejectUserOrder(String orderId) {
		Userorder order = orderRepository.getOne(orderId);
		if (order.getOrderStatus().equalsIgnoreCase("Order Cancelled")) {
			throw new CantRejectCancelledOrderException("You can't Reject already cancelled order!!");
		}
		order.setOrderStatus("Rejected");
		refundOrderAmount(order.getUser().getId(), orderId);
		orderRepository.save(order);

	}

	@Override
	public void orderOutForDelivery(String id) {
		Userorder order = orderRepository.getOne(id);
		if (order.getOrderStatus().equalsIgnoreCase("Order Cancelled")) {
			throw new OrderAlreadyCancelledException("Order is already cancelled by the user");
		}

		if (order.getOrderStatus().equalsIgnoreCase("Out for Delivery")) {
			throw new OrderAlreadyOutForDeliveryException("Order is already gone out for Delivery!!");
		}

		if (order.getOrderStatus().equalsIgnoreCase("Rejected")) {
			throw new CantSendRejectedOrderException("You can't send rejected order out for delivery!!");
		}
		order.setOrderStatus("Out for Delivery");
		orderRepository.save(order);
	}

	// get all food items
	public List<FoodItems> getFoodItems() {
		return foodItemsRepository.findAll();
	}

	// get food items of particular id
	public FoodItems getFoodItemsById(String itemId) {
		return foodItemsRepository.findByItemId(itemId);
	}

	// add new food items
	@Override
	public FoodItems saveFoodItems(String restaurantId, FoodItems foodItems) {
		List<FoodItems> fooditems = new ArrayList<>();
		Restaurant restaurant1 = new Restaurant();

		Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId);
		if (restaurant == null) {
			throw new RestaurantNotFoundException("Restaurant not found!");
		}
		// Restaurant restaurant = ((Optional<Userorder>) restaurant).get();
		foodItems.setRestaurant(restaurant);
		FoodItems fooditems1 = foodItemsRepository.save(foodItems);
		fooditems.add(fooditems1);
		restaurant1.setFooditems(fooditems);
		return fooditems1;
	}

	// update food items
	@Override
	public FoodItems updateFoodItemsById(String itemId, FoodItems itemRequest) {
		if (!foodItemsRepository.existsById(itemId)) {
			throw new FoodItemNotFoundException("Food Item not found");
		}
		Optional<FoodItems> foodItems = foodItemsRepository.findById(itemId);

		if (!foodItems.isPresent()) {
			throw new FoodItemNotFoundException("Food Item not found");
		}

		FoodItems fooditems1 = foodItems.get();
		fooditems1.setItemName(itemRequest.getItemName());
		fooditems1.setItemPrice(itemRequest.getItemPrice());
		fooditems1.setItemQuantity(itemRequest.getItemQuantity());
		fooditems1.setItemAvailability(itemRequest.getItemAvailability());
		fooditems1.setItemType(itemRequest.getItemType());

		return foodItemsRepository.save(fooditems1);
	}

	// delete food items of particular id
	public String deleteFoodItems(String itemId) {
		foodItemsRepository.deleteById(itemId);
		return "food item removed and food item Id=" + itemId;
	}

	@Override
	public void deliveredOrder(String orderId) {
		Userorder order = orderRepository.getOne(orderId);
		if (order.getOrderStatus().equalsIgnoreCase("Order Cancelled")) {
			throw new CantConfirmCancelledOrderException(
					"You can't confirm delivery for already cancelled order by user!!");

		} else if (order.getOrderStatus().equalsIgnoreCase("Rejected")) {
			throw new CantConfirmRejectOrderException(
					"You can't confirm delivery for already Rejected order by admin!!");

		} else if (order.getOrderStatus().equalsIgnoreCase("Delivered")) {
			throw new CantDeliverAgainOrderException("Order is already Delivered to the user!!");

		} else if (order.getOrderStatus().equalsIgnoreCase("Requested")) {
			throw new OrderMustBeAcceptedException("Order must be accepted by admin before delivery!!");
		}
		order.setOrderStatus("Delivered");
		orderRepository.save(order);

	}

}
