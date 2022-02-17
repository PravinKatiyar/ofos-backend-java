package com.pravin.onlinefoodordersys.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

import com.pravin.onlinefoodordersys.util.StringPrefixedSequenceIdGenerator;
@Component
@Entity
@Table(name = "restaurant")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq")
	@GenericGenerator(name = "restaurant_seq", strategy = "com.pravin.onlinefoodordersys.util.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "4"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "R_"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
	private String restaurantId;
	private String restaurantName;
	private String ownerName;
	private String phoneNumber;
	private String email;
	private String address;
	private long cashcounter;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<FoodItems> fooditems = new ArrayList<>();

	public Restaurant() {
	}

	public Restaurant(String restaurantId, String restaurantName, String ownerName, String phoneNumber, String email,
			String address, long cashcounter, List<FoodItems> fooditems) {
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.ownerName = ownerName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.cashcounter = cashcounter;
		this.fooditems = fooditems;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<FoodItems> getFooditems() {
		return fooditems;
	}

	public void setFooditems(List<FoodItems> fooditems) {
		this.fooditems = fooditems;
	}

	public long getCashcounter() {
		return cashcounter;
	}

	public void setCashcounter(long cashcounter) {
		this.cashcounter = cashcounter;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", ownerName="
				+ ownerName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address
				+ ", cashcounter=" + cashcounter + ", fooditems=" + fooditems + "]";
	}

}
