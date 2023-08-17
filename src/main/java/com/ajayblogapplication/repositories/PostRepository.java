package com.ajayblogapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajayblogapplication.entities.Category;
import com.ajayblogapplication.entities.Post;
import com.ajayblogapplication.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String title);
}
