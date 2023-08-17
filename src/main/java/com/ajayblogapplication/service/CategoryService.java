package com.ajayblogapplication.service;

import java.util.List;

import com.ajayblogapplication.payload.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto getCategoryById(Integer categoryId);

	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	void deleteCategory(Integer categoryId);

	List<CategoryDto> getAllCategory();

}
