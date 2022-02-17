package com.pravin.onlinefoodordersys.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pravin.onlinefoodordersys.util.StringPrefixedSequenceIdGenerator;


@Entity
public class FoodItems {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@GenericGenerator(name = "user_seq", strategy = "com.pravin.onlinefoodordersys.util.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "3"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "F_"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
	private String itemId;
	private String itemName;
	private double itemPrice;
	private int itemQuantity;
	private String itemAvailability;
	private String itemType;

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurantId", nullable = false)
	@JsonIgnore
	private Restaurant restaurant;
	
	public FoodItems() {
	}
	public FoodItems(String itemId, String itemName, double itemPrice, int itemQuantity, String itemAvailability,
			String itemType, Restaurant restaurant) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		this.itemAvailability = itemAvailability;
		this.itemType = itemType;
		this.restaurant = restaurant;
	}
	
	public String getRestaurantName() {
		return restaurant.getRestaurantName();
	}
	
	public String getRestaurantId() {
		return restaurant.getRestaurantId();
	}
	


	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemAvailability() {
		return itemAvailability;
	}

	public void setItemAvailability(String itemAvailability) {
		this.itemAvailability = itemAvailability;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	@JsonIgnore
	public Restaurant getRestaurant() {
		return restaurant;
	}
	@JsonIgnore
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}



}
