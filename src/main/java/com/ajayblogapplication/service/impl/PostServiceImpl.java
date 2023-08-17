package com.ajayblogapplication.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.ajayblogapplication.entities.Category;
import com.ajayblogapplication.entities.Post;
import com.ajayblogapplication.entities.User;
import com.ajayblogapplication.exception.ResourceNotFoundException;
import com.ajayblogapplication.payload.PostDto;
import com.ajayblogapplication.payload.PostResponse;
import com.ajayblogapplication.repositories.CategoryRepository;
import com.ajayblogapplication.repositories.CommentRepository;
import com.ajayblogapplication.repositories.PostRepository;
import com.ajayblogapplication.repositories.UserRepository;
import com.ajayblogapplication.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		Post post = mapToEntity(postDto);
		post.setAddedDate(new Date());
		post.setImageName("default.png");
		post.setUser(user);
		post.setCategory(category);
		Post newPost = postRepo.save(post);

		return mapToDto(newPost);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		return mapToDto(post);

	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());

//      Sort sort = null;
//		if (sortDir.equalsIgnoreCase("asc")) {
//			sort = Sort.by(sortBy).ascending();
//		} else {
//			sort = Sort.by(sortBy).descending();
//		}

		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = postRepo.findAll(pageRequest);
		List<Post> contents = pagePost.getContent();

		List<PostDto> postsDto = contents.stream().map(content -> mapToDto(content)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postsDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPage(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = postRepo.save(post);
		return mapToDto(updatedPost);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		postRepo.delete(post);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		List<Post> posts = postRepo.findByUser(user);

		return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = postRepo.findByTitleContaining(keyword);
		return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
	}

	public Post mapToEntity(PostDto postDto) {
		return modelMapper.map(postDto, Post.class);
	}

	public PostDto mapToDto(Post post) {
		return modelMapper.map(post, PostDto.class);
	}

}
