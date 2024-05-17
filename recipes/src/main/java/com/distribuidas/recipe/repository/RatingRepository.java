package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByRecipeID(Integer recipeID);

    @Modifying
    @Query(value = "UPDATE ratings SET details = NULL WHERE recipeID = ?1", nativeQuery = true)
    void deleteDetail(Integer recipeID);
}
