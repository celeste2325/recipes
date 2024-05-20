package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.model.entities.Review;
import com.distribuidas.recipe.service.interfaces.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ReviewController {
    private ReviewService ratingService;

    @PostMapping
    public ResponseEntity<Review> rateRecipe(@RequestBody Review newReview) {
        return new ResponseEntity<>(this.ratingService.rateRecipe(newReview), HttpStatus.CREATED);
    }

    @GetMapping("{recipeID}")
    public ResponseEntity<List<Review>> getRecipeRatings(@PathVariable Integer recipeID) {
        return new ResponseEntity<>(this.ratingService.getRecipeReviewsByRecipeID(recipeID), HttpStatus.OK);
    }

    @DeleteMapping("{reviewID}")
    public void deleteRating(@PathVariable Integer reviewID) {
        this.ratingService.removeRecipeReview(reviewID);
    }

}
