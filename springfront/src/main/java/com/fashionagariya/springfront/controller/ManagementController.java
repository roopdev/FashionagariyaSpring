package com.fashionagariya.springfront.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fashionagariya.springback.dao.CategoryDAO;
import com.fashionagariya.springback.dao.ProductDAO;
import com.fashionagariya.springback.dto.Category;
import com.fashionagariya.springback.dto.Product;
import com.fashionagariya.springfront.util.FileUploadUtility;
import com.fashionagariya.springfront.validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation) {
		
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title","Manage Products");
		
		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("product", nProduct);
		
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message", "Success: Product submitted!");
			}
			else if(operation.equals("category")) {
				mv.addObject("message", "Success: Category submitted!");
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)
	public ModelAndView editProducts(@PathVariable int id) {
		
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title","Manage Products");
		
		Product nProduct = productDAO.get(id);
		mv.addObject("product", nProduct);
		
		return mv;
	}
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String productSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model, HttpServletRequest request) {
		
		// image custom validation
		if(mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, results);
		}
		else {
			if(!mProduct.getFile().getContentType().equals("")) {
				new ProductValidator().validate(mProduct, results);
			}
		}
		
		
		if(results.hasErrors()) {
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title","Manage Products");
			model.addAttribute("message", "Failed: Validation error!");
			
			return "page";
		}
		
		if(mProduct.getId() == 0) {
			// For new product creation
			productDAO.add(mProduct);
		}
		else {
			// For updating existing product
			productDAO.update(mProduct);
		}
		
		
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String productActivation(@PathVariable("id") int id) {
		
		Product product = productDAO.get(id);	
		boolean isActive = product.isActive();
		product.setActive(!product.isActive());
		productDAO.update(product);
		
		return (isActive)? "Success: Product De-activated" + product.getId() : "Success: Product activated" + product.getId();
	}
	
	@RequestMapping(value="/category", method = RequestMethod.POST)
	public String addCategory(@ModelAttribute Category category) {
		
		categoryDAO.add(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}

}
