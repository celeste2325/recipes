package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByDescriptionLikeIgnoreCase(String s);
}
