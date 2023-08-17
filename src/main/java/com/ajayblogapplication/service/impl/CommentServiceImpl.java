package com.ajayblogapplication.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajayblogapplication.entities.Comment;
import com.ajayblogapplication.entities.Post;
import com.ajayblogapplication.exception.ResourceNotFoundException;
import com.ajayblogapplication.payload.CommentDto;
import com.ajayblogapplication.repositories.CommentRepository;
import com.ajayblogapplication.repositories.PostRepository;
import com.ajayblogapplication.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = mapToEntity(commentDto);
		comment.setPost(post);
		Comment saveComment = commentRepo.save(comment);
		return mapToDto(saveComment);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		commentRepo.delete(comment);

	}

	@Override
	public CommentDto getCommentById(Integer commentId) {
		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		return mapToDto(comment);

	}

	public Comment mapToEntity(CommentDto commentDto) {
		return modelMapper.map(commentDto, Comment.class);
	}

	public CommentDto mapToDto(Comment comment) {
		return modelMapper.map(comment, CommentDto.class);
	}

}
