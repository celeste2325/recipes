package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.exception.RecipeDoesNotExistException;
import com.distribuidas.recipe.exception.ExistingRecipeException;
import com.distribuidas.recipe.model.dto.ReplaceRecipeResponseDto;
import com.distribuidas.recipe.model.entities.*;
import com.distribuidas.recipe.repository.*;
import com.distribuidas.recipe.service.interfaces.RecipeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private ReviewRepository ratingRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    AuthorizationStatusRepository authorizationStatusRepository;

    @Override
    public Recipe createRecipe(Recipe newRecipe) throws ExistingRecipeException {
        if (recipeByUserAndRecipeName(newRecipe.getName(), newRecipe.getUserByUserID().getUserID()) == null) {
            Date dateOfRecipe = new Date();
            dateOfRecipe.setDateCreation(LocalDateTime.now());
            newRecipe.setDateByRecipeID(dateOfRecipe);
            dateOfRecipe.setRecipeByRecipeID(newRecipe);


            Recipe recipe = this.recipeRepository.save(newRecipe);

            AuthorizationStatus authorizationStatus = new AuthorizationStatus();
            authorizationStatus.setStatusType("No autorizado");
            authorizationStatus.setEntityID(recipe.getRecipeID());
            authorizationStatus.setEntityType("Receta");
            this.authorizationStatusRepository.save(authorizationStatus);
            return recipe;
        }
        throw new ExistingRecipeException("Estimado/a, ya creo una receta con mismo nombre");
    }

    @Override
    public Recipe updateRecipe(Integer ID, Recipe newRecipe) throws RecipeDoesNotExistException {
        Optional<Recipe> recipeFound = this.recipeRepository.findById(ID);
        if (recipeFound.isPresent()) {
            //se eliminan los comentarios de la receta
            if (!recipeFound.get().getReviewByRecipeID().isEmpty()) {
                this.deleteComments(recipeFound.get().getRecipeID());
            }

            //update fields
            recipeFound.get().setName(newRecipe.getName());
            recipeFound.get().setDescription(newRecipe.getDescription());
            recipeFound.get().setUrlPhoto(newRecipe.getUrlPhoto());
            recipeFound.get().setPhotosByRecipeID(newRecipe.getPhotosByRecipeID());
            recipeFound.get().setServings(newRecipe.getServings());
            recipeFound.get().setNumberPeople(newRecipe.getNumberPeople());
            recipeFound.get().setCategoryByCategoryID(newRecipe.getCategoryByCategoryID());
            recipeFound.get().setStepsByRecipeID(newRecipe.getStepsByRecipeID());
            recipeFound.get().setIngredientsUsedByRecipeID(newRecipe.getIngredientsUsedByRecipeID());


            recipeFound.get().getIngredientsUsedByRecipeID().forEach(utilizado -> {
                utilizado.setRecipeByRecipeID(recipeFound.get());
                var unit =  new Unit();
                unit.setUnitID(utilizado.getUnitID());
                utilizado.setUnitsOfMeasurementByUnitID(unit);

                var ingredient = new Ingredient();
                ingredient.setIngredientID(utilizado.getIngredientID());
                utilizado.setIngredientsByIngredientID(ingredient);
            });

            recipeFound.get().getStepsByRecipeID().forEach(paso -> {
                paso.setRecipeByRecipeID(recipeFound.get());
            });



            return this.recipeRepository.save(recipeFound.get());
        }
        throw new RecipeDoesNotExistException("Don't exist recipe for this IDNo existe una receta asociada al id ingresado");
    }

    @Override
    public void removeRecipe(Integer ID) throws RecipeDoesNotExistException {
        Optional<Recipe> recipeFound = this.recipeRepository.findById(ID);
        if (recipeFound.isPresent()) {
            this.recipeRepository.delete(recipeFound.get());
        } else {
            throw new RecipeDoesNotExistException("No recipe for ID introduced");
        }
    }
    //TODO ESTA OK
    @Override
    @Transactional
    public List<Recipe> getRecipes(Integer userID) {
        return this.recipeRepository.getRecipes(userID);
    }

    @Override
    public Recipe recipeByUserAndRecipeName(String recipeName, Integer userID) {
        return this.recipeRepository.findByNameAndUserID(recipeName, userID);
    }

    //TODO esta ok
    @Override
    public List<Recipe> getRecipesByNameOrderByUserName(String recipeName, Integer userID) {
        return this.recipeRepository.findByRecipeNameOrderByUserName(recipeName, userID);
    }
    //TODO esta ok
    @Override
    public List<Recipe> getRecipesByNameOrderByOldest(String recipeName, Integer userID) {
        return this.recipeRepository.findByRecipeNameOrderByOldest(recipeName, userID);
    }
    //TODO esta ok
    @Override
    public List<Recipe> getRecipesByParam(String recipeName, Integer typeID, Integer ingredientID, Integer userID, Integer mandatoryUserID) {
        return this.recipeRepository.recipesByParamQuery(recipeName, typeID, ingredientID, userID, mandatoryUserID);
    }

    @Transactional
    @Override
    public ReplaceRecipeResponseDto replaceRecipe(Integer recipeID) throws RecipeDoesNotExistException {
        Optional<Recipe> recipeFound = this.recipeRepository.findById(recipeID);
        ReplaceRecipeResponseDto reemplazarRecetaResponseDto = new ReplaceRecipeResponseDto();
        if (recipeFound.isPresent()) {
            reemplazarRecetaResponseDto.setName(recipeFound.get().getName());
            //se eliminan los comentarios de la receta pero se mantienen las calificaciones
            if (!recipeFound.get().getReviewByRecipeID().isEmpty()) {
                this.deleteComments(recipeFound.get().getRecipeID());
                reemplazarRecetaResponseDto.setReviewByRecipeID(recipeFound.get().getReviewByRecipeID());
            }
            //implementar eliminacion logica
            //TODO eliminar cuando la receta este creada
            //this.eliminarReceta(recetaEncotrada.get().getIdReceta());
            return reemplazarRecetaResponseDto;
        }
        throw new RecipeDoesNotExistException("No recipe for ID introduced");
    }

    private void deleteComments(Integer recipeID) {
        this.ratingRepository.deleteComment(recipeID);
    }

    //TODO esta ok
    @Override
    public Recipe getRecipeByID(Integer ID, Integer userID) throws RecipeDoesNotExistException {
        Optional<Recipe> recipe = this.recipeRepository.getRecipeByID(ID, userID);
        if (recipe.isPresent()) {
            return recipe.get();
        }
        throw new RecipeDoesNotExistException("No recipe for ID introduced");
    }

    @Override
    public List<Recipe> getRecipesWithoutIngredientOrderByDate(Integer ingredientID, Integer userID) {
        return this.recipeRepository.getRecipesWithoutIngredientsOrderByDate(ingredientID, userID);
    }
    @Override
    public List<Recipe> getRecipesWithoutIngredientsOrderByName(Integer ingredientID, Integer userID) {
        return this.recipeRepository.recipesWithoutIngredientOrderByRecipeName(ingredientID, userID);
    }
    //TODO ESTA OK
    @Override
    public List<Recipe> getRecipesWithoutIngredientsOrderByUserName(Integer ingredientID, Integer userID) {
        return this.recipeRepository.recipesWithoutIngredientOrderByUserName(ingredientID, userID);
    }

    //TODO ESTA OK
    @Override
    public List<Recipe> getRecipesByPartialName(String partialRecipeName, Integer userID) {
        if (!Objects.equals(partialRecipeName, "")) {
            return recipeRepository.findByRecipeNameLikeIgnoreCase(partialRecipeName, userID);
        }
        return null;
    }

    //TODO ESTA OK
    @Override
    public List<Recipe> getThreeRecipesStartApp(Integer userID) {
        return this.recipeRepository.findByUserIdOrderByDate(userID);
    }

}
