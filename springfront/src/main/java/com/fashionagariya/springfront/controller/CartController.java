package com.fashionagariya.springfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fashionagariya.springfront.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name="result", required=false)String result) {
		ModelAndView mv = new ModelAndView("page");
		
		if(result!=null) {
			switch(result) {
			
			case "updated":
				mv.addObject("message", "Success: Cart updated!!");
				break;
			case "added":
				mv.addObject("message", "Success: Cart added!!");
				break;
			case "deleted":
				mv.addObject("message", "Success: Cart removed!!");
				break;
			case "maximum":
				mv.addObject("message", "Failure: Product count maximum!!");
				break;
			case "unavailable":
				mv.addObject("message", "Failure: Product quantity not available!!");
				break;
			case "error":
				mv.addObject("message", "Failure: Update failed!!");
				break;
			}
		}
		mv.addObject("title", "My Cart");
		mv.addObject("userClickShowCart", true);
		mv.addObject("cartLines", cartService.getCartLines());
		
		return mv;
	}
	
	@RequestMapping("/{cartLineId}/update")
	public String updateCart(@PathVariable int cartLineId, @RequestParam int count) {
		String response = cartService.manageCartLine(cartLineId, count);
		return "redirect:/cart/show?"+response;
	}
	
	@RequestMapping("/{cartLineId}/delete")
	public String deleteCart(@PathVariable int cartLineId) {
		String response = cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?"+response;
	}
	
	@RequestMapping("/add/{productId}/product")
	public String addCart(@PathVariable int productId) {
		String response = cartService.addCartLine(productId);
		return "redirect:/cart/show?"+response;
	}


}
