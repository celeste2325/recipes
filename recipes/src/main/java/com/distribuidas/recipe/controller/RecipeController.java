package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.exception.ExistingRecipeException;
import com.distribuidas.recipe.exception.RecipeDoesNotExistException;
import com.distribuidas.recipe.model.dto.RecipeDto;
import com.distribuidas.recipe.model.dto.response.RecipeResponseDto;
import com.distribuidas.recipe.model.entities.Recipe;
import com.distribuidas.recipe.model.mapstruct.RecipeMapper;
import com.distribuidas.recipe.service.interfaces.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    @PostMapping()
    public ResponseEntity<?> createRecipe(@RequestBody Recipe newRecipe) {
        try {
            return new ResponseEntity<Recipe>(this.recipeService.createRecipe(newRecipe), HttpStatus.CREATED);
        } catch (ExistingRecipeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{ID}")
    public ResponseEntity<?> editRecipe(@PathVariable Integer ID, @RequestBody RecipeDto newRecipe) {
        try {
            Recipe newRecipeEntity = this.recipeMapper.mapToEntity(newRecipe);
            return new ResponseEntity<>(this.recipeMapper.mapResponseDto(this.recipeService.updateRecipe(ID, newRecipeEntity)), HttpStatus.CREATED);
        } catch (RecipeDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/replace/{recipeID}")
    public ResponseEntity<?> replaceRecipe(@PathVariable Integer recipeID) {
        try {
            return new ResponseEntity<>(this.recipeService.replaceRecipe(recipeID), HttpStatus.OK);
        } catch (RecipeDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Integer ID) {
        try {
            this.recipeService.removeRecipe(ID);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RecipeDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/startApp")
    public ResponseEntity<?> getRecipesStartingApp(@RequestParam(defaultValue = "0") Integer userID) {
        return new ResponseEntity<>(this.recipeMapper.mapLisToDto(this.recipeService.getThreeRecipesStartApp(userID)), HttpStatus.OK);
    }

    @GetMapping("byUserName/{recipeName}")
    public ResponseEntity<?> getRecipesByNameOrderByUserName(@PathVariable String recipeName,
                                                             @RequestParam(defaultValue = "0") Integer userID) {
        return new ResponseEntity<>(this.recipeMapper.mapLisToDto(
                this.recipeService.getRecipesByNameOrderByUserName(recipeName, userID)), HttpStatus.OK);
    }

    @GetMapping("orderByAntiquity/{recipeName}")
    public ResponseEntity<?> getRecipesByNameOrderByAntiquity(@PathVariable String recipeName, @RequestParam(defaultValue = "0") Integer userID) {
        return new ResponseEntity<>(this.recipeService.getRecipesByNameOrderByAntiquity(recipeName, userID), HttpStatus.OK);
    }

    //TODO ok
    @GetMapping()
    public ResponseEntity<List<RecipeResponseDto>> getRecipes(@RequestParam(defaultValue = "0") Integer userID) {
        return new ResponseEntity<>(this.recipeMapper.mapLisToDto(this.recipeService.getRecipes(userID)), HttpStatus.OK);
    }
    //TODO ok
    @GetMapping("/{userID}/{recipeName}")
    public ResponseEntity<?> recipeByUserAndRecipeName(@PathVariable Integer userID, @PathVariable String recipeName) throws ExistingRecipeException {
        Recipe recipe = this.recipeService.recipeByUserAndRecipeName(recipeName, userID);
        if (recipe == null) {
            return ResponseEntity.badRequest().body("No recipe for userID and recipe name introduced");
        } else {
            return new ResponseEntity<>(recipe, HttpStatus.OK);

        }
    }
    //TODO ok
    @GetMapping("recipeById/{ID}")
    public ResponseEntity<?> getRecipeByID(@PathVariable Integer ID, @RequestParam(defaultValue = "0") Integer userID) {
        try {
            return new ResponseEntity<>(this.recipeMapper.mapResponseDto(this.recipeService.getRecipeByID(ID, userID)), HttpStatus.OK);
        } catch (RecipeDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/request-param")
    public ResponseEntity<List<RecipeResponseDto>> getRecipesByParamQuery(@RequestParam(defaultValue = "") String recipeName,
                                                                          @RequestParam(defaultValue = "0") Integer typeID,
                                                                          @RequestParam(defaultValue = "0") Integer ingredientID,
                                                                          @RequestParam(defaultValue = "0") Integer userID,
                                                                          @RequestParam(defaultValue = "0") Integer mandatoryUserID
    ) {
        return new ResponseEntity<>(this.recipeMapper.mapLisToDto(this.recipeService.getRecipesByParam(recipeName, typeID, ingredientID, userID, mandatoryUserID)), HttpStatus.OK);
    }

    @GetMapping("withoutIngredient/recipeName/{ingredientID}")
    public ResponseEntity<?> getRecipesWithoutIngredientsOrderByName(@PathVariable Integer ingredientID,
                                                                     @RequestParam(defaultValue = "0") Integer userID) {
        return new ResponseEntity<>(this.recipeMapper.
                mapLisToDto(this.recipeService.getRecipesWithoutIngredientsOrderByName(ingredientID, userID)),
                HttpStatus.OK);

    }

    @GetMapping("withoutIngredient/antiquity/{ingredientID}")
    public ResponseEntity<?> getRecipeWithoutIngredientOrderByAntiquity(@PathVariable Integer ingredientID,
                                                                        @RequestParam(defaultValue = "0") Integer userID) {
        return new ResponseEntity<>(this.recipeMapper.mapLisToDto(this.recipeService.getRecipesWithoutIngredientOrderByDate(ingredientID, userID)), HttpStatus.OK);

    }

    @GetMapping("withoutIngredient/userName/{ingredientID}")
    public ResponseEntity<?> getRecipesWithoutIngredientsOrderByUserName(@PathVariable Integer ingredientID,
                                                                         @RequestParam(defaultValue = "0") Integer userID) {
        return new ResponseEntity<>(this.recipeMapper.mapLisToDto(this.recipeService.getRecipesWithoutIngredientsOrderByUserName(ingredientID, userID)), HttpStatus.OK);

    }

    @GetMapping("partialSearch/{partialRecipeName}")
    public ResponseEntity<List<RecipeResponseDto>> getRecipesByPartialName(@PathVariable String partialRecipeName,
                                                                           @RequestParam(defaultValue = "0") Integer userID) {
        if (partialRecipeName.length() >= 2) {
            return new ResponseEntity<>(this.recipeMapper.mapLisToDto(this.recipeService.getRecipesByPartialName(partialRecipeName, userID)), HttpStatus.OK);
        }
        return null;
    }
}
