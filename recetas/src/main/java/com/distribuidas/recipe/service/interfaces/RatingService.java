package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.model.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating rateRecipe(Rating newRating);

    List<Rating> getRecipeRatingsByRecipeID(Integer recipeID);

    void removeRecipeRating(Integer ratingID);
}
