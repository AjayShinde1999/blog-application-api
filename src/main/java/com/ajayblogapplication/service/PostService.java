package com.ajayblogapplication.service;

import java.util.List;
import com.ajayblogapplication.payload.PostDto;
import com.ajayblogapplication.payload.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	PostDto getPostById(Integer postId);

	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	// get all posts by category

	List<PostDto> getPostsByCategory(Integer categoryId);

	List<PostDto> getPostsByUser(Integer userId);
	
	List<PostDto> searchPost(String keyword);

}
