package com.distribuidas.recipe.model.dto;

import com.distribuidas.recipe.model.entities.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class RecipeDto {
    private Integer recipeID;
    private String name;
    private String description;
    private Integer servings;
    private Integer numberPeople;
    private User usersByUserID;
    private Category categoriesByCategoryID;
    private String urlPhoto;
    private Collection<PhotoRecipe> photosByRecipeID;
    private List<Step> stepsByRecipeID;
    private List<IngredientUsed> IngredientUsedByRecipeID;
    private Collection<Review> reviewByRecipeID;

    private Collection<Favorite> favoritosByIdReceta;
    private Collection<PhotoRecipe> fotosByIdReceta;





}
