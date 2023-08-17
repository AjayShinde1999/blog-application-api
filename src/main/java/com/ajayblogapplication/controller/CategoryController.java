package com.ajayblogapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajayblogapplication.payload.ApiResponse;
import com.ajayblogapplication.payload.CategoryDto;
import com.ajayblogapplication.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/save")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createCategory = categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryId") Integer categoryId) {
		CategoryDto categoryById = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(categoryById, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		List<CategoryDto> allCategory = categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(allCategory, HttpStatus.OK);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable("categoryId") Integer categoryId) {
		CategoryDto updateCategory = categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(new ApiResponse("Deleted Successfully", true), HttpStatus.OK);
	}

}
