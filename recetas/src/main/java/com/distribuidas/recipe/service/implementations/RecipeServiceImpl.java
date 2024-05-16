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
    private RatingRepository ratingRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    AuthorizationStatusRepository authorizationStatusRepository;

    @Override
    public Recipe createRecipe(Recipe newRecipe) throws ExistingRecipeException {
        if (recipeByUser(newRecipe.getNombre(), newRecipe.getUsuariosByIdUsuario().getIdUsuario()) == null) {
            DateOfRecipe dateOfRecipe = new DateOfRecipe();
            dateOfRecipe.setFechaCreacion(LocalDateTime.now());
            newRecipe.setFechaRecetaByIdReceta(dateOfRecipe);
            dateOfRecipe.setRecetaByIdReceta(newRecipe);


            Recipe recipe = this.recipeRepository.save(newRecipe);

            AuthorizationStatus authorizationStatus = new AuthorizationStatus();
            authorizationStatus.setTipoEstado("No autorizado");
            authorizationStatus.setIdEntidad(recipe.getIdReceta());
            authorizationStatus.setTipoEntidad("Receta");
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
            if (!recipeFound.get().getCalificacionesByIdReceta().isEmpty()) {
                this.eliminarComentarios(recipeFound.get().getIdReceta());
            }

            //update fields
            recipeFound.get().setNombre(newRecipe.getNombre());
            recipeFound.get().setDescripcion(newRecipe.getDescripcion());
            recipeFound.get().setFoto(newRecipe.getFoto());
            recipeFound.get().setFotosByIdReceta(newRecipe.getFotosByIdReceta());
            recipeFound.get().setPorciones(newRecipe.getPorciones());
            recipeFound.get().setCantidadPersonas(newRecipe.getCantidadPersonas());
            recipeFound.get().setTiposByIdTipo(newRecipe.getTiposByIdTipo());
            recipeFound.get().setPasosByIdReceta(newRecipe.getPasosByIdReceta());
            recipeFound.get().setUtilizadosByIdReceta(newRecipe.getUtilizadosByIdReceta());


            recipeFound.get().getUtilizadosByIdReceta().forEach(utilizado -> {
                utilizado.setRecetasByIdReceta(recipeFound.get());
                var unit =  new UnitOfMeasurement();
                unit.setIdUnidad(utilizado.getIdUnidad());
                utilizado.setUnidadesByIdUnidad( unit);

                var ingredient = new Ingredient();
                ingredient.setIdIngrediente(utilizado.getIdIngrediente());
                utilizado.setIngredientesByIdIngrediente(ingredient);
            });

            recipeFound.get().getPasosByIdReceta().forEach(paso -> {
                paso.setRecetasByIdReceta(recipeFound.get());
            });



            return this.recipeRepository.save(recipeFound.get());
        }
        throw new RecipeDoesNotExistException("Don't exist receip for this IDNo existe una receta asociada al id ingresado");
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
    public List<Recipe> getRecipes(Integer userID) {
        return this.recipeRepository.devolverTodasLasrecetasHabilitadas(userID);
    }

    @Override
    public Recipe recipeByUser(String recipeName, Integer userID) {
        return this.recipeRepository.findByNombreAndIdUsuario(recipeName, userID);
    }

    //TODO esta ok
    @Override
    public List<Recipe> getRecipesByNameOrderByUserName(String recipeName, Integer userID) {
        return this.recipeRepository.findByNombreOrderByNombreUser(recipeName, userID);
    }
    //TODO esta ok
    @Override
    public List<Recipe> getRecipesByNameOrderByAntiquity(String recipeName, Integer userID) {
        return this.recipeRepository.findByNombreOrderByAntiguedad(recipeName, userID);
    }
    //TODO esta ok
    @Override
    public List<Recipe> getRecipesByParam(String recipeName, Integer typeID, Integer ingredientID, Integer userID, Integer mandatoryUserID) {
        return this.recipeRepository.recetasByParamQuery(recipeName, typeID, ingredientID, userID, mandatoryUserID);
    }

    @Transactional
    @Override
    public ReplaceRecipeResponseDto replaceRecipe(Integer recipeID) throws RecipeDoesNotExistException {
        Optional<Recipe> recipeFound = this.recipeRepository.findById(recipeID);
        ReplaceRecipeResponseDto reemplazarRecetaResponseDto = new ReplaceRecipeResponseDto();
        if (recipeFound.isPresent()) {
            reemplazarRecetaResponseDto.setNombre(recipeFound.get().getNombre());
            //se eliminan los comentarios de la receta pero se mantienen las calificaciones
            if (!recipeFound.get().getCalificacionesByIdReceta().isEmpty()) {
                this.eliminarComentarios(recipeFound.get().getIdReceta());
                reemplazarRecetaResponseDto.setCalificacionesByIdReceta(recipeFound.get().getCalificacionesByIdReceta());
            }
            //implementar eliminacion logica
            //TODO eliminar cuando la receta este creada
            //this.eliminarReceta(recetaEncotrada.get().getIdReceta());
            return reemplazarRecetaResponseDto;
        }
        throw new RecipeDoesNotExistException("No recipe for ID introduced");
    }

    private void eliminarComentarios(Integer idReceta) {
        this.ratingRepository.eliminarComentariosDeReceta(idReceta);
    }

    //TODO esta ok
    @Override
    public Recipe getRecipeByID(Integer ID, Integer userID) throws RecipeDoesNotExistException {
        Optional<Recipe> recipe = this.recipeRepository.devuelveRecetasPorId(ID, userID);
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
        return this.recipeRepository.recetasSinIngredientesOrdenadaPorNombre(ingredientID, userID);
    }
    //TODO ESTA OK
    @Override
    public List<Recipe> getRecipesWithoutIngredientsOrderByUserName(Integer ingredientID, Integer userID) {
        return this.recipeRepository.recetasSinIngredientesOrdenadaPorNombreUser(ingredientID, userID);
    }

    //TODO ESTA OK
    @Override
    public List<Recipe> getRecipesByPartialName(String partialRecipeName, Integer userID) {
        if (!Objects.equals(partialRecipeName, "")) {
            return recipeRepository.findByNombreLikeIgnoreCase(partialRecipeName, userID);
        }
        return null;
    }

    //TODO ESTA OK
    @Override
    public List<Recipe> getThreeRecipesStartApp(Integer userID) {
        return this.recipeRepository.recetasPorUsuarioOrdenadasPorFecha(userID);
    }

}
