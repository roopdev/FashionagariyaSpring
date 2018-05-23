package com.fashionagariya.springback.dao;

import java.util.List;

import com.fashionagariya.springback.dto.Cart;
import com.fashionagariya.springback.dto.CartLine;
import com.fashionagariya.springback.dto.OrderDetail;

public interface CartLineDAO {
	
	public List<CartLine> list(int cartId);
	public CartLine get(int id);
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean remove(CartLine cartLine);
	
	public List<CartLine> listAvailable(int cartId);
	public CartLine getByCartAndProduct(int cartId, int productId);
	
	// Methods for Cart
	boolean updateCart(Cart cart);
	
	// adding order details
	boolean addOrderDetails(OrderDetail orderDetail);

}
