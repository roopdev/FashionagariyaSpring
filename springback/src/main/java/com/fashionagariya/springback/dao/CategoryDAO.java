package com.fashionagariya.springback.dao;

import java.util.List;

import com.fashionagariya.springback.dto.Category;

public interface CategoryDAO {
		
	Category get(int id); // to get a category
	List<Category> list(); // to fetch list of categories
	boolean add(Category category); // to add a category 
	boolean update(Category category); // to update a category 
	boolean delete(Category category); // to delete a category 

}
