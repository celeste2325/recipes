package com.distribuidas.recipe.repository;

import com.distribuidas.recipe.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByRecipeID(Integer recipeID);

    @Modifying
    @Query(value = "UPDATE reviews SET details = NULL WHERE recipeID = ?1", nativeQuery = true)
    void deleteComment(Integer recipeID);
}
