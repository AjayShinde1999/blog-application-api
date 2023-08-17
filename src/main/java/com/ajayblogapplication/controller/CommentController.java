package com.ajayblogapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ajayblogapplication.payload.ApiResponse;
import com.ajayblogapplication.payload.CommentDto;
import com.ajayblogapplication.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // http://localhost:8080/api/post/1/comment/save
    @PostMapping("/post/{postId}/comment/save")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@RequestBody CommentDto commentDto, @PathVariable("postId") Integer postId) {
        return commentService.createComment(commentDto, postId);
    }

    @DeleteMapping("/comment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse deleteComment(@PathVariable("commentId") Integer commentId) {
        commentService.deleteComment(commentId);
        return new ApiResponse("Comment Deleted Successfully", true);
    }

    // http://localhost:8080/comment/1
    @GetMapping("/comment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getComment(@PathVariable("commentId") Integer commentId) {
        return commentService.getCommentById(commentId);
    }

}
