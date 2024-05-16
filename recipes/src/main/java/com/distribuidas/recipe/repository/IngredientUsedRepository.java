package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.IngredientUsed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientUsedRepository extends JpaRepository<IngredientUsed, Integer> {
}
