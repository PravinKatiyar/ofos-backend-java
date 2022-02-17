package com.pravin.onlinefoodordersys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pravin.onlinefoodordersys.model.Userorder;
@Repository
public interface OrderRepository extends JpaRepository<Userorder, String> {
	
 @Query(value= "SELECT * From user_order o WHERE o.user_id=?",nativeQuery=true)
  public List<Userorder> findByUserId(String userId);

 @Query(value= "SELECT user_address From user_order o WHERE o.order_id=?",nativeQuery=true)
 public String findAddress(String orderId);

 @Query(value= "select * from user_order where order_status!='cancel'",nativeQuery=true)
public Iterable<Userorder> viewAllnonCancelledOrders();
 
 @Query(value= "SELECT * From user_order o WHERE o.order_id=?",nativeQuery=true)
 public Userorder findorderByorderId(String orderId);

 
	
	

}
