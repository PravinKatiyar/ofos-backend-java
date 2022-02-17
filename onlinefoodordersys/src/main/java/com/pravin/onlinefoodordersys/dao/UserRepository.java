package com.pravin.onlinefoodordersys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pravin.onlinefoodordersys.model.User;

@Repository 
public interface UserRepository extends JpaRepository<User, String>{

	public User findByEmailIgnoreCase(String username);

	public User findUserByContactNo(String contactNo);

	public User findUserByEmail(String email);

	public User findUserById(String id);
	
	public void deleteById(String id);
	
	public void deleteByEmail(String email);

}
