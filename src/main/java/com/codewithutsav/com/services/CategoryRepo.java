package com.codewithutsav.com.services;

import java.util.List;

import com.codewithutsav.com.payloads.CategoryDto;

public interface CategoryRepo {
	
	//create
    CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	 CategoryDto updateCategory(CategoryDto categoryDto ,Integer categoryId);
	//Delete
	 void deleteCategory(Integer categoryId);
	//getSingleuser
	 CategoryDto getCategory(Integer categoryId);
	
	//get all user
	List<CategoryDto> getCategories();
	
	

}
