package com.fashionagariya.springback.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fashionagariya.springback.dao.UserDAO;
import com.fashionagariya.springback.dto.Address;
import com.fashionagariya.springback.dto.User;

public class UserTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	/*private Cart cart = null;*/
	private Address address = null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.fashionagariya.springback");
		context.refresh();
		
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
/*	@Test
	public void testAddUser() {
		
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		// add the user
		assertEquals("Failed to add the user!", true, userDAO.addUser(user));
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		
		address.setUserId(user.getId());
		
		// add the address
		assertEquals("Failed to add the billing address!", true, userDAO.addAddress(address));
		
		if(user.getRole().equals("USER")) {
			cart = new Cart();
			cart.setUser(user);
			
			// add the cart
			assertEquals("Failed to add the billing cart!", true, userDAO.addCart(cart));
			
			// shipping address
			// add the shipping address
			address = new Address();
			address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
			address.setAddressLineTwo("Near Kudrat Store");
			address.setCity("Mumbai");
			address.setState("Maharashtra");
			address.setCountry("India");
			address.setPostalCode("400001");
			address.setShipping(true);
			
			address.setUserId(user.getId());
			
			assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
		}
	}*/
	
	/*@Test
	public void testAddUser() {
		
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");	
		
		if(user.getRole().equals("USER")) {
			cart = new Cart();
			cart.setUser(user);		
			
			user.setCart(cart);
		}
		// add the user
    	assertEquals("Failed to add the user!", true, userDAO.addUser(user));
	}*/
	
	/*@Test
	public void testUpateCart() {
		user = userDAO.getByEmail("hr@gmail.com");
		
		cart = user.getCart();
		cart.setGrandTotal(5555);
		cart.setCartLines(2);
    	assertEquals("Failed to add the cart!", true, userDAO.updateCart(cart));
	}*/
	
/*	@Test
	public void testAddAddress() {
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		// add the user
		assertEquals("Failed to add the user!", true, userDAO.addUser(user));
		
		// billing address
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		
		address.setUser(user);
		
		assertEquals("Failed to add the address!", true, userDAO.addAddress(address));
		
		// shipping address
		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setShipping(true);
		
		address.setUser(user);
		
		assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
	}*/
	
	@Test
	public void testAddAddress() {
		
		user = userDAO.getByEmail("hr@gmail.com");
		
		// shipping address
		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Bangalore");
		address.setState("Karnataka");
		address.setCountry("India");
		address.setPostalCode("560001");
		address.setShipping(true);
		
		address.setUser(user);
		
		assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
	}

}
