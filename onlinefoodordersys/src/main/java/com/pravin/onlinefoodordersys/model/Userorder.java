package com.pravin.onlinefoodordersys.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pravin.onlinefoodordersys.util.StringPrefixedSequenceIdGenerator;
@Component
@Entity
@Table(name = "user_order")
public class Userorder implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userorder_seq")
	@GenericGenerator(name = "userorder_seq", strategy = "com.pravin.onlinefoodordersys.util.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "6"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "UORDR_"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
	private String orderId;//

	private String orderDateTime;//

	private double totalCost;//

	private String orderStatus;//

	private int quantity;//

	private String restaurantName;//

	private String restaurantPhoneNumber;//

	private String restaurantEmail;//

	private String restaurantId;//

	private String itemId;//

	private String restaurantAddress;//

	private String itemName;//

	private double itemPrice;//

	@ManyToOne()
	@JoinColumn(name = "userId", nullable = false)
	//@JsonIgnore
	private User user;

	public Userorder() {
		super();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantPhoneNumber() {
		return restaurantPhoneNumber;
	}

	public void setRestaurantPhoneNumber(String restaurantPhoneNumber) {
		this.restaurantPhoneNumber = restaurantPhoneNumber;
	}

	public String getRestaurantEmail() {
		return restaurantEmail;
	}

	public void setRestaurantEmail(String restaurantEmail) {
		this.restaurantEmail = restaurantEmail;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "Userorder [orderId=" + orderId + ", orderDateTime=" + orderDateTime + ", totalCost=" + totalCost
				+ ", orderStatus=" + orderStatus + ", quantity=" + quantity + ", restaurantName=" + restaurantName
				+ ", restaurantPhoneNumber=" + restaurantPhoneNumber + ", restaurantEmail=" + restaurantEmail
				+ ", restaurantId=" + restaurantId + ", itemId=" + itemId + ", restaurantAddress=" + restaurantAddress
				+ ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", user=" + user + "]";
	}
	

}