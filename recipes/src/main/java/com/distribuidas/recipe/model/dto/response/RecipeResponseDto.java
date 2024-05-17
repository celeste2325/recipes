package com.distribuidas.recipe.model.dto.response;

import com.distribuidas.recipe.model.entities.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class RecipeResponseDto {
    private Integer recipeID;
    private String name;
    private String description;
    private Integer servings;
    private DateOfRecipe dateOfRecipeByRecipeID;
    private Integer numberPeople;
    private User usersByUserID;
    private Category categoriesByCategoryID;
    private String urlPhoto;
    private Collection<PhotoInstruction> photosByRecipeID;
    private List<Step> stepsByRecipeID;
    private List<IngredientUsed> IngredientUsedByRecipeID;
}
