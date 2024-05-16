package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.exception.RecipeDoesNotExistException;
import com.distribuidas.recipe.exception.ExistingRecipeException;
import com.distribuidas.recipe.model.dto.ReplaceRecipeResponseDto;
import com.distribuidas.recipe.model.entities.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe createRecipe(Recipe newRecipe) throws ExistingRecipeException;

    Recipe updateRecipe(Integer ID, Recipe newRecipe) throws RecipeDoesNotExistException;

    void removeRecipe(Integer ID) throws RecipeDoesNotExistException;

    List<Recipe> getRecipes(Integer userID);

    Recipe recipeByUser(String recipeName, Integer userID) throws ExistingRecipeException;

    Recipe getRecipeByID(Integer ID, Integer userID) throws RecipeDoesNotExistException;
    List<Recipe> getRecipesWithoutIngredientOrderByDate(Integer ingredientID, Integer userID);
    List<Recipe> getRecipesWithoutIngredientsOrderByName(Integer ingredientID, Integer userID);

    List<Recipe> getRecipesByPartialName(String partialRecipeName, Integer userID);

    List<Recipe> getThreeRecipesStartApp(Integer userID);

    List<Recipe> getRecipesByNameOrderByUserName(String recipeName, Integer userID);

    List<Recipe> getRecipesByNameOrderByAntiquity(String recipeName, Integer userID);

    List<Recipe> getRecipesByParam(String recipeName, Integer typeID, Integer ingredientID, Integer userID, Integer mandatoryUserID);
    ReplaceRecipeResponseDto replaceRecipe(Integer recipeID) throws RecipeDoesNotExistException;

    List<Recipe> getRecipesWithoutIngredientsOrderByUserName(Integer ingredientID, Integer userID);
}
