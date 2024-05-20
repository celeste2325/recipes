package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.model.entities.Review;

import java.util.List;

public interface ReviewService {

    Review rateRecipe(Review newReview);

    List<Review> getRecipeReviewsByRecipeID(Integer recipeID);

    void removeRecipeReview(Integer reviewID);
}
