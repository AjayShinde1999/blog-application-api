package com.ajayblogapplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajayblogapplication.entities.Category;
import com.ajayblogapplication.exception.ResourceNotFoundException;
import com.ajayblogapplication.payload.CategoryDto;
import com.ajayblogapplication.repositories.CategoryRepository;
import com.ajayblogapplication.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = mapToEntity(categoryDto);
		Category newCategory = categoryRepo.save(category);
		return mapToDto(newCategory);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		return mapToDto(category);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = categoryRepo.save(category);
		return mapToDto(updatedCategory);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		categoryRepo.delete(category);

	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepo.findAll();
		return categories.stream().map(category -> mapToDto(category)).collect(Collectors.toList());

	}

	public Category mapToEntity(CategoryDto dto) {
		return modelMapper.map(dto, Category.class);
	}

	public CategoryDto mapToDto(Category category) {
		return modelMapper.map(category, CategoryDto.class);
	}

}
