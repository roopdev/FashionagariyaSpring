package com.fashionagariya.springback.dao;

import java.util.List;

import com.fashionagariya.springback.dto.Product;

public interface ProductDAO {
	
	Product get(int productId); // to get a product
	List<Product> list(); // to fetch list of categories
	boolean add(Product product); // to add a product 
	boolean update(Product product); // to update a product 
	boolean delete(Product product); // to delete a product 
	
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
	
	List<Product> getProductsByParam(String param, int count);

}
