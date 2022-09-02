package com.codewithutsav.com.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.codewithutsav.com.entities.Category;
import com.codewithutsav.com.exceptions.ResourceNotFoundException;
import com.codewithutsav.com.payloads.CategoryDto;
import com.codewithutsav.com.repositories.CategoryRepo;
import com.codewithutsav.com.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	//create
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat=this.modelMapper.map(categoryDto ,Category.class);
		Category addedCat=this.categoryRepo.save(cat);
		
		
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}
    //update
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		
	
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatecat=this.categoryRepo.save(cat);
		
		return this.modelMapper.map(updatecat, CategoryDto.class);
		
		
		
	}

	//delete
	@Override
	public void deleteCategory(Integer categoryId) {
		
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
		this.categoryRepo.delete(cat);
		
	}
    //single user
	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	//all user
	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto> catDtos=categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}

}
