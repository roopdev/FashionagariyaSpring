package com.fashionagariya.springback.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fashionagariya.springback.dao.CategoryDAO;
import com.fashionagariya.springback.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	
	private static List<Category> categories = new ArrayList<>();
	
	static {
		Category category = new Category();
		
		// 1
		category.setId(1);
		category.setName("Clothing");
		category.setDescription("Description for clothing");
		category.setImageURL("CAT_1.png");
		
		categories.add(category);
		
		// 2 
		category = new Category();
		category.setId(2);
		category.setName("Footwear");
		category.setDescription("Description for footwear");
		category.setImageURL("CAT_2.png");
		
		categories.add(category);
		
		// 3
		category = new Category();
		category.setId(3);
		category.setName("Jewellery");
		category.setDescription("Description for jewellery");
		category.setImageURL("CAT_3.png");
		
		categories.add(category);
		
		// 4
		category = new Category();
		category.setId(4);
		category.setName("Bags");
		category.setDescription("Description for bags");
		category.setImageURL("CAT_4.png");
		
		categories.add(category);
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category get(int id) {
		
		for(Category category : categories) {
			if(category.getId() == id) return category;
		}
		return null;
	}

}
