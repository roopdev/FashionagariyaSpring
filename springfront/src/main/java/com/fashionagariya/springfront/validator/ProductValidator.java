package com.fashionagariya.springfront.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fashionagariya.springback.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Product product = (Product) target;
		
		// Check if file is selected or not
		if(product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
			
			errors.rejectValue("file", null, "Please upload image file!");
			return;
		}
		
		if(! (product.getFile().getContentType().equals("image/jpeg") || 
			  product.getFile().getContentType().equals("image/png") ||
			  product.getFile().getContentType().equals("image/gif")
			  )) {
			errors.rejectValue("file", null, "Please use .jpg or .png or .gif image file.");
			return;
		}
	}

}
