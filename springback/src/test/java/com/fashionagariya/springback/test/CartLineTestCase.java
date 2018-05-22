package com.fashionagariya.springback.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fashionagariya.springback.dao.CartLineDAO;
import com.fashionagariya.springback.dao.ProductDAO;
import com.fashionagariya.springback.dao.UserDAO;
import com.fashionagariya.springback.dto.Cart;
import com.fashionagariya.springback.dto.CartLine;
import com.fashionagariya.springback.dto.Product;
import com.fashionagariya.springback.dto.User;

public class CartLineTestCase {
	
	private Product product = null;
	private User user = null;
	private Cart cart = null;
	private CartLine cartLine = null;
	
	private static AnnotationConfigApplicationContext context;
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.fashionagariya.springback");
		context.refresh();
		userDAO = (UserDAO)context.getBean("userDAO");
		productDAO = (ProductDAO)context.getBean("productDAO");
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");		
	}
	
	@Test
	public void testAddNewCartLine() {
		
		// Get the user
		user = userDAO.getByEmail("test@test.com");
		// fetch the cart of user
		cart = user.getCart();
		// get product to add
		product = productDAO.get(1);
		// new CartLine
		cartLine = new CartLine();
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		
		assertEquals("Failed",true, cartLineDAO.add(cartLine));
		
		// Update the cart
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1);
		
		assertEquals("Failed", true, cartLineDAO.updateCart(cart));
	}

}
