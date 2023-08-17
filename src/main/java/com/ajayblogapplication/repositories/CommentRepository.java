package com.ajayblogapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajayblogapplication.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
