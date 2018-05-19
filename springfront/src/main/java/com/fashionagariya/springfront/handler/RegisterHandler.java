package com.fashionagariya.springfront.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fashionagariya.springback.dao.UserDAO;
import com.fashionagariya.springback.dto.Address;
import com.fashionagariya.springback.dto.Cart;
import com.fashionagariya.springback.dto.User;
import com.fashionagariya.springfront.model.RegisterModel;

@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public RegisterModel init() {
		
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel model) {
		String transitionValue = "success";
		
		User user = model.getUser();
		if(user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		// encode the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userDAO.addUser(user);
		
		Address billing = model.getBilling();
		billing.setUser(user);
		billing.setBilling(true);
		
		userDAO.addAddress(billing);
				
		return transitionValue;
	}
	
	public String validateUser(User user, MessageContext error) {
		String transitionValue = "success";
		
		if(!(user.getPassword().equals(user.getConfirmPassword()))) {
			error.addMessage(new MessageBuilder().error().source("confirmPassword").defaultText("Confirm password dest not match.").build());
			transitionValue = "failure";
		}
		
		if(userDAO.getByEmail(user.getEmail())!=null) {
			error.addMessage(new MessageBuilder().error().source("email").defaultText("Email id already exists.").build());
			transitionValue = "failure";
		}
		
		return transitionValue;
	}

}
