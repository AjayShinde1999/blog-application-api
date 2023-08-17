package com.ajayblogapplication.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private Integer postId;

	private String title;

	private String content;

	private Date addedDate;

	private String imageName;

	private CategoryDto category;

	private UserDto user;
	
//	private CommentDto comment;

}
