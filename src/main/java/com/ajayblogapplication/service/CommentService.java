package com.ajayblogapplication.service;

import com.ajayblogapplication.payload.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);

	CommentDto getCommentById(Integer commentId);

}
