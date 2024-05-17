package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    Ingredient findByName(String name);

    List<Ingredient> findByNameLikeIgnoreCase(String name);
}
