package com.fashionagariya.springfront.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashionagariya.springback.dao.CartLineDAO;
import com.fashionagariya.springback.dao.ProductDAO;
import com.fashionagariya.springback.dto.Cart;
import com.fashionagariya.springback.dto.CartLine;
import com.fashionagariya.springback.dto.Product;
import com.fashionagariya.springfront.model.UserModel;

@Service("cartService")
public class CartService {
	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private HttpSession session;
	
	//User Model Cart from session
	private Cart getCart() {		
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	
	// Entire cart lines
	public List<CartLine> getCartLines() {
		return cartLineDAO.list(this.getCart().getId());
	}

	public String manageCartLine(int cartLineId, int count) {
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine == null) {
			return "result=error";
		}
		else {
			Product product = cartLine.getProduct();			
			double oldTotal = cartLine.getTotal();
			
			if(product.getQuantity() < count) {
				return "result=unavailable";
			}
			
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice() * count);
			cartLineDAO.update(cartLine);
			
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			return "result=updated";
		}
	}

	public String deleteCartLine(int cartLineId) {
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine == null) {
			return "result=error";
		}
		else {
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDAO.updateCart(cart);
			
			cartLineDAO.remove(cartLine);
			
			return "result=deleted";
		}
	}

	public String addCartLine(int productId) {
		String response = null;
		Cart cart = this.getCart();
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine == null) {
			cartLine = new CartLine();
			Product product = productDAO.get(productId);
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			cartLineDAO.add(cartLine);
			
			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			response = "result=added";
		}
		else {
			if(cartLine.getProductCount() < 3) {
				// Update product count
				response = this.manageCartLine(cartLine.getId(), cartLine.getProductCount() + 1);
			}
			else {
				response = "result=maximum";
			}
		}
		
		return response;
	}

}
