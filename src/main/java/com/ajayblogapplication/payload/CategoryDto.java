package com.ajayblogapplication.payload;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	private Integer categoryId;

	@NotEmpty(message = "Not be empty")
	private String categoryTitle;

	@NotEmpty(message = "Not be empty")
	private String categoryDescription;
}
