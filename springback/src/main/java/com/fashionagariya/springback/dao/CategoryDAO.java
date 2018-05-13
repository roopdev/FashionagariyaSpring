package com.fashionagariya.springback.dao;

import java.util.List;

import com.fashionagariya.springback.dto.Category;

public interface CategoryDAO {
	
	List<Category> list();
	Category get(int id);

}
