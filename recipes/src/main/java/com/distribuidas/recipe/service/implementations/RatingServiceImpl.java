package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.model.entities.Rating;
import com.distribuidas.recipe.repository.RatingRepository;
import com.distribuidas.recipe.service.interfaces.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating rateRecipe(Rating newRating) {
        return this.ratingRepository.save(newRating);
    }

    @Override
    public List<Rating> getRecipeRatingsByRecipeID(Integer recipeID) {
        return this.ratingRepository.findByIdReceta(recipeID);
    }

    @Override
    public void removeRecipeRating(Integer ratingID) {
        this.ratingRepository.deleteById(ratingID);
    }
}
