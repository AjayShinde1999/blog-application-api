package com.ajayblogapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajayblogapplication.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
