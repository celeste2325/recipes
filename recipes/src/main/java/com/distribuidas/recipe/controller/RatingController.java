package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.model.entities.Rating;
import com.distribuidas.recipe.service.interfaces.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RatingController {
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> rateRecipe(@RequestBody Rating newRating) {
        return new ResponseEntity<>(this.ratingService.rateRecipe(newRating), HttpStatus.CREATED);
    }

    @GetMapping("{recipeID}")
    public ResponseEntity<List<Rating>> getRecipeRatings(@PathVariable Integer recipeID) {
        return new ResponseEntity<>(this.ratingService.getRecipeRatingsByRecipeID(recipeID), HttpStatus.OK);
    }

    @DeleteMapping("{ratingID}")
    public void deleteRating(@PathVariable Integer ratingID) {
        this.ratingService.removeRecipeRating(ratingID);
    }

}
