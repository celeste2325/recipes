package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.model.entities.Review;
import com.distribuidas.recipe.repository.ReviewRepository;
import com.distribuidas.recipe.service.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review rateRecipe(Review newReview) {
        return this.reviewRepository.save(newReview);
    }

    @Override
    public List<Review> getRecipeReviewsByRecipeID(Integer recipeID) {
        return this.reviewRepository.findByRecipeID(recipeID);
    }

    @Override
    public void removeRecipeReview(Integer reviewID) {
        this.reviewRepository.deleteById(reviewID);
    }
}
