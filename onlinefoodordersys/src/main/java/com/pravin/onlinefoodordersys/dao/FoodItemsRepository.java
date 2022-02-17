package com.pravin.onlinefoodordersys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pravin.onlinefoodordersys.model.FoodItems;
import com.pravin.onlinefoodordersys.model.Userorder;
@Repository
public interface FoodItemsRepository extends JpaRepository <FoodItems,String>{
	
	
	public FoodItems findByItemId(String itemId);
	
	@Query(value="select * from Food_items f WHERE f.item_availability = 'yes'",nativeQuery = true)
	List<FoodItems> findAllActiveFoodItems();

	@Query(value ="select * from Food_items f WHERE f.restaurant_id = ?1", nativeQuery = true)
	List<FoodItems> findItemsByRestaurant(String restaurantId);
	
	
	@Query(value= "select item_price from Food_items f WHERE f.item_id =?1", nativeQuery= true)
	float findItemsPrice(String fooditemId);

	@Query(value ="select * from Food_items f WHERE f.restaurant_id = ?1 and f.item_availability = 'yes' and item_quantity >0", nativeQuery = true)
	public List<FoodItems> fetchFoodItemByRestaurantavailablefoodItems(String restaurantId);
	
	
	
	
}
