package com.pravin.onlinefoodordersys.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pravin.onlinefoodordersys.model.Restaurant;
import com.pravin.onlinefoodordersys.model.Userorder;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,String>{

	 @Query(value= "SELECT * From restaurant r WHERE r.restaurant_id=?",nativeQuery=true)	  
	Restaurant findByRestaurantId(String restaurantId);


}
