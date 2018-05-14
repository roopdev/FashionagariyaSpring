package com.fashionagariya.springback.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fashionagariya.springback.dao.CategoryDAO;
import com.fashionagariya.springback.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.fashionagariya.springback");
		context.refresh();
		
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
		
	}
	
	/*@Test
	public void testAddCategory() {
		
		category = new Category();
		category.setName("Bags");
		category.setDescription("Description for bags");
		category.setImageURL("CAT_4.png");
		
		assertEquals("Failure: Category not added to table.", true,categoryDAO.add(category));
	}*/
	
	
	/*@Test
	public void testGetCategory() {
		
		category = categoryDAO.get(3);
		
		assertEquals("Failure: Category from table.", "Jewellery",category.getName());
		System.out.println(category);
	}*/
	
	/*@Test
	public void testUpdateCategory() {
		category = categoryDAO.get(3);
		
		category.setDescription("New description of jewellery");
		
		assertEquals("Failure: Category updated!", true, categoryDAO.update(category));
	}*/
	
	/*@Test
	public void testDeleteCategory() {
		category = categoryDAO.get(3);
		
		assertEquals("Failure: Category Deleted!", true, categoryDAO.delete(category));
	}*/
	
	@Test
	public void testListCategory() {
		
		assertEquals("Failure: Categories from table.", 3, categoryDAO.list().size());
	}
	
	
	

}
