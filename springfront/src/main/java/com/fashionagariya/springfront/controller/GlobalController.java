package com.fashionagariya.springfront.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fashionagariya.springback.dao.UserDAO;
import com.fashionagariya.springback.dto.User;
import com.fashionagariya.springfront.model.UserModel;

@ControllerAdvice
public class GlobalController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDAO userDAO;
	
	private UserModel userModel = null;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel() {
		
		if(session.getAttribute("userModel")==null) {
			// add UserModel
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			User user = userDAO.getByEmail(authentication.getName());
			if(user!=null) {
				// create new UserModel object
				userModel = new UserModel();
				userModel.setId(user.getId());
				userModel.setFullName(user.getFirstName() + " " + user.getLastName());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				
				if(userModel.getRole().equals("USER")) {
					userModel.setCart(user.getCart());
				}
				
				// set UserModel in session
				session.setAttribute("userModel", userModel);
				
				return userModel;
			}
		}
		
		return (UserModel) session.getAttribute("userModel");
		
	}

}
