package com.fashionagariya.springfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fashionagariya.springback.dao.OrderDetailDAO;

@Controller
@RequestMapping("/user")
public class UserController {	
	
	@Autowired
	private OrderDetailDAO orderDetailDAO;
	
	@RequestMapping("/{userId}/orders")
	private ModelAndView getUserOrders(@PathVariable("userId") int userId) {
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("title","My Orders");
		mv.addObject("UserClickShowOrders",true);
		mv.addObject("OrderDetails", orderDetailDAO.list(userId));
		
		return mv;
	}

}
