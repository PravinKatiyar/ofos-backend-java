package com.pravin.onlinefoodordersys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pravin.onlinefoodordersys.dao.UserRepository;
import com.pravin.onlinefoodordersys.exception.ContactNumberAlreadyExistException;
import com.pravin.onlinefoodordersys.exception.EmailAlreadyExistsException;
import com.pravin.onlinefoodordersys.model.User;
import com.pravin.onlinefoodordersys.security.JwtuserFactory;
import com.pravin.onlinefoodordersys.util.PasswordUtil;

@Service
@Transactional
public class AuthServiceImpl implements UserDetailsService,AuthService{
	
	@Autowired
	private  UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =userRepository.findByEmailIgnoreCase(username);
		if(user==null) {
			throw new UsernameNotFoundException("No User found with username"+ user.getFirstName()+" "+user.getLastName());
		}
		else {
			return JwtuserFactory.create(user);
		}}

	@Override
	public User registerUser(User user) {
		if (userRepository.findUserByEmail(user.getEmail()) != null)
			throw new EmailAlreadyExistsException("user with Email " + user.getEmail() + " is Already Exist");
		
		if (userRepository.findUserByContactNo(user.getContactNo()) != null)
			throw new ContactNumberAlreadyExistException(
					"customer with ContactNumber " + user.getContactNo()+ " is Already Exist");
		
		user.setWalletBalance(100);
		user.setEnabled(true);		
		String password=PasswordUtil.getPasswordHash(user.getPassword());
		user.setPassword(password);
		
		System.out.println(user);
		return userRepository.save(user);
	} 


}
