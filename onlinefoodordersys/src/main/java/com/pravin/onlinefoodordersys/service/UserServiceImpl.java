package com.pravin.onlinefoodordersys.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pravin.onlinefoodordersys.dao.FoodItemsRepository;
import com.pravin.onlinefoodordersys.dao.OrderRepository;
import com.pravin.onlinefoodordersys.dao.RestaurantRepository;
import com.pravin.onlinefoodordersys.dao.UserRepository;
import com.pravin.onlinefoodordersys.exception.FoodItemNotFoundException;
import com.pravin.onlinefoodordersys.exception.InsufficientBalanceexception;
import com.pravin.onlinefoodordersys.exception.InvalidItemQuantityException;
import com.pravin.onlinefoodordersys.exception.OrderAlreadyCancelledException;
import com.pravin.onlinefoodordersys.exception.OrderCancelException;
import com.pravin.onlinefoodordersys.exception.RestaurantNotFoundException;
import com.pravin.onlinefoodordersys.exception.UserDoesNotExistException;
import com.pravin.onlinefoodordersys.model.FoodItems;
import com.pravin.onlinefoodordersys.model.Restaurant;
import com.pravin.onlinefoodordersys.model.User;
import com.pravin.onlinefoodordersys.model.Userorder;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private FoodItemsRepository foodItemsRepository;

	@Autowired
	private Userorder userOrder;

	@Autowired
	private Restaurant restaurant;

	@Override
	public List<User> findAll() {
		List<User> usersList = userRepository.findAll();
		return usersList;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmailIgnoreCase(email);
		return user;
	}

	@Override
	public boolean creditWallet(String userId, int addedbalance) {
		if (userRepository.existsById(userId) == true) {
			User user1 = userRepository.findUserById(userId);
			user1.setWalletBalance(user1.getWalletBalance() + addedbalance);
			System.out.println(user1.getWalletBalance());
			userRepository.save(user1);

			return true;
		} else {
			throw new UserDoesNotExistException("User does not exists");
		}
	}

	@Override
	public boolean debitWallet(String userId, int orderprice, String restaurantId) throws InsufficientBalanceexception {
		if (userRepository.existsById(userId) == true) {
			User user1 = userRepository.findUserById(userId);
			if (user1.getWalletBalance() < orderprice) {
				throw new InsufficientBalanceexception(
						"Insufficient Wallet Balance!!, Your Balance is" + user1.getWalletBalance());
			}
			user1.setWalletBalance(user1.getWalletBalance() - orderprice);
			Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId);
			restaurant.setCashcounter(restaurant.getCashcounter() + orderprice);

			userRepository.save(user1);
			return true;
		} else {
			throw new UserDoesNotExistException("User does not exists");
		}
	}

	public List<Userorder> getAllOrdersByCustomerId(String userId) {
		String user = userRepository.findUserById(userId).getId();
		List<Userorder> orders = orderRepository.findByUserId(user);
		return orders;
	}

	@Override
	public Userorder addOrder(int itemQuantity, String customerId, String fooditemId) throws InsufficientBalanceexception {

		return userRepository.findById(customerId).map(user -> {
			Userorder userorder = new Userorder();
			// checking item quantity in DB
			int itemquantityfromdb = foodItemsRepository.findByItemId(fooditemId).getItemQuantity();
			if (itemQuantity > itemquantityfromdb) {
				throw new InvalidItemQuantityException("Requested Quantity is not available at the moment");
			}

			// fetching Current date time
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			userorder.setOrderDateTime(dtf.format(now));
			userorder.setUser(user);

			// find price of food item
			float price = foodItemsRepository.findItemsPrice(fooditemId);
			int quantity = itemQuantity;

			// setting quantity
			userorder.setQuantity(quantity);

			// calculating total cost
			float totalCost = price * quantity;
			userorder.setTotalCost(totalCost);

			// debit amount from client account and crediting to repective restaurant
			debitWallet(customerId, (int) userorder.getTotalCost(),
					foodItemsRepository.findByItemId(fooditemId).getRestaurantId());

			// setting order status
			userorder.setOrderStatus("Requested");

			FoodItems foodItem = foodItemsRepository.findById(fooditemId)
					.orElseThrow(() -> new FoodItemNotFoundException("Fooditem not found!"));

			int availableQuantity = foodItem.getItemQuantity();
			int itemquantity = itemQuantity;
			foodItem.setItemQuantity(availableQuantity - itemquantity);
			userorder.setItemName(foodItem.getItemName());
			userorder.setItemPrice(foodItem.getItemPrice());
			userorder.setItemId(foodItem.getItemId());
			userorder.setRestaurantAddress(foodItem.getRestaurant().getAddress());
			userorder.setRestaurantEmail(foodItem.getRestaurant().getEmail());
			userorder.setRestaurantId(foodItem.getRestaurant().getRestaurantId());
			userorder.setRestaurantName(foodItem.getRestaurant().getRestaurantName());
			userorder.setRestaurantPhoneNumber(foodItem.getRestaurant().getPhoneNumber());

//			userorder.setFoodItems(temptest);			
//			userorder.setUserAddress(user.getAddress());

			return orderRepository.save(userorder);
		}).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found!"));
	}

	@Override
	public void cancelOrder(String orderId) {
		Userorder order = orderRepository.getOne(orderId);
		order.getUser().getId();
		order.getTotalCost();

		String orderstatus = order.getOrderStatus();

		if (orderstatus.equalsIgnoreCase("Order Cancelled")) {
			throw new OrderAlreadyCancelledException("Your order is already cancelled!!");
		}

		refundOrderAmount(order.getUser().getId(), orderId);

		order.setOrderStatus("Order Cancelled");
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
	public List<FoodItems> findFoodItemsbyRestaurant(String restaurantId) {
		return foodItemsRepository.findItemsByRestaurant(restaurantId);
	}

	@Override
	public List<FoodItems> findAllAvailableFoodItems() {
		return foodItemsRepository.findAllActiveFoodItems();
	}

	@Override
	public List<FoodItems> fetchFoodItemByRestaurantavailablefoodItems(String restaurantId) {
		return foodItemsRepository.fetchFoodItemByRestaurantavailablefoodItems(restaurantId);
	}

	@Override
	public Userorder viewOrderById(String orderId) {
		return orderRepository.findorderByorderId(orderId);
	}

}
