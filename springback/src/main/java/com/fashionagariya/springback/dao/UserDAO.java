package com.fashionagariya.springback.dao;

import java.util.List;

import com.fashionagariya.springback.dto.Address;
import com.fashionagariya.springback.dto.User;

public interface UserDAO {
	// Methods for User
	boolean addUser(User user);
	User getByEmail(String email);
	User getUser(int id);
	
	// Methods for Address
	Address getAddress(int addressId);
	boolean addAddress(Address address);
	boolean updateAddress(Address address);
	Address getBillingAddress(User user);
	List<Address> listShippingAddress(User user);
	


}
