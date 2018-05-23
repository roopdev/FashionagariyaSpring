package com.fashionagariya.springfront.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fashionagariya.springback.dao.CartLineDAO;
import com.fashionagariya.springback.dao.ProductDAO;
import com.fashionagariya.springback.dao.UserDAO;
import com.fashionagariya.springback.dto.Address;
import com.fashionagariya.springback.dto.Cart;
import com.fashionagariya.springback.dto.CartLine;
import com.fashionagariya.springback.dto.OrderDetail;
import com.fashionagariya.springback.dto.OrderItem;
import com.fashionagariya.springback.dto.Product;
import com.fashionagariya.springback.dto.User;
import com.fashionagariya.springfront.model.CheckoutModel;
import com.fashionagariya.springfront.model.UserModel;

@Component
public class CheckoutHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(CheckoutHandler.class);
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private HttpSession session;
	
	public CheckoutModel init(String name) throws Exception{

		User user = userDAO.getByEmail(name);
		CheckoutModel checkoutModel = null;	

		if(user!=null) {
			checkoutModel = new CheckoutModel();
			checkoutModel.setUser(user);
			checkoutModel.setCart(user.getCart());
			
			double checkoutTotal = 0.0;
			List<CartLine> cartLines = cartLineDAO.listAvailable(user.getCart().getId());

			if(cartLines.size() == 0 ) {
				throw new Exception("There are no products available for checkout now!");
			}
			
			for(CartLine cartLine: cartLines) {
				checkoutTotal += cartLine.getTotal();
			}
			
			checkoutModel.setCartLines(cartLines);
			checkoutModel.setCheckoutTotal(checkoutTotal);			
		}			
		
		return checkoutModel;
	}
	
	
	public List<Address> getShippingAddresses(CheckoutModel model) {
		
		List<Address> addresses = userDAO.listShippingAddress(model.getUser());
		
		if(addresses.size() == 0) {
			addresses = new ArrayList<>();
		}
		
		addresses.add(addresses.size(), userDAO.getBillingAddress(model.getUser()));
		
		return addresses;
	}
	
	public String saveAddressSelection(CheckoutModel checkoutModel, int shippingId) {
		
		String transitionValue = "success";
		logger.info(String.valueOf(shippingId));
		
		Address shipping = userDAO.getAddress(shippingId);
		
		checkoutModel.setShipping(shipping);
		
		return transitionValue;
	}
	
	public String saveAddress(CheckoutModel checkoutModel, Address shipping) {
		String transitionValue = "success";
		// set UserId & shipping true
		shipping.setUser(checkoutModel.getUser());
		shipping.setShipping(true);
		userDAO.addAddress(shipping);
		
		// Set shippingId to flowScope Object
		checkoutModel.setShipping(shipping);
		
		return transitionValue;
	}
	
	public String saveOrder(CheckoutModel checkoutModel) {
		String transitionValue = "success";
		// New order object
		OrderDetail orderDetail = new OrderDetail();
		// Attach the user
		orderDetail.setUser(checkoutModel.getUser());
		// Attach shipping Address
		orderDetail.setShipping(checkoutModel.getShipping());
		// fetch billing address
		Address billing = userDAO.getBillingAddress(checkoutModel.getUser());
		orderDetail.setBilling(billing);
		// fetch cartlines
		List<CartLine> cartLines = checkoutModel.getCartLines();
		OrderItem orderItem = null;
		double orderTotal = 0.0;
		int orderCount = 0;
		Product product = null;
		
		for(CartLine cartLine : cartLines) {
			orderItem = new OrderItem();
			orderItem.setBuyingPrice(cartLine.getBuyingPrice());
			orderItem.setProduct(cartLine.getProduct());
			orderItem.setProductCount(cartLine.getProductCount());
			orderItem.setTotal(cartLine.getTotal());
			// set order details to order item
			orderItem.setOrderDetail(orderDetail);
			// add order item to order detail
			orderDetail.getOrderItems().add(orderItem);
			
			orderTotal += orderItem.getTotal();
			orderCount++;
			
			// update product , reduce quantity of product
			product = cartLine.getProduct();
			product.setQuantity(product.getQuantity() - cartLine.getProductCount());
			product.setPurchases(product.getPurchases() + cartLine.getProductCount());
			productDAO.update(product);
			
			// delete the cartLine for the user
			cartLineDAO.remove(cartLine);
		}
		
		orderDetail.setOrderTotal(orderTotal);
		orderDetail.setOrderCount(orderCount);
		orderDetail.setOrderDate(new Date());
		// Save the order
		cartLineDAO.addOrderDetails(orderDetail);
		// set order details of checkout model
		checkoutModel.setOrderDetail(orderDetail);
		
		// update cart
		Cart cart = checkoutModel.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - orderTotal);
		cart.setCartLines(cart.getCartLines() - orderCount);
		cartLineDAO.updateCart(cart);
		
		// update cart if present in session
		UserModel userModel = (UserModel)session.getAttribute("userModel");
		if(userModel!=null) {
			userModel.setCart(cart);
		}
		
		return transitionValue;
	}
	
	public OrderDetail getOrderDetail(CheckoutModel checkoutModel) {
		return checkoutModel.getOrderDetail();
	}

}
