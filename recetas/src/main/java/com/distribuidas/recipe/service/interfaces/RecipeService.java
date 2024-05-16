package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.exception.RecipeDoesNotExistException;
import com.distribuidas.recipe.exception.ExistingRecipeException;
import com.distribuidas.recipe.model.dto.ReplaceRecipeResponseDto;
import com.distribuidas.recipe.model.entities.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe altaReceta(Recipe newReceta) throws ExistingRecipeException;

    Recipe updateReceta(Integer id, Recipe newReceta) throws RecipeDoesNotExistException;

    void eliminarReceta(Integer id) throws RecipeDoesNotExistException;

    List<Recipe> devolverRecetas(Integer idUsuario);

    Recipe recetaExistentePorUsuario(String nombreReceta, Integer idUsuario) throws ExistingRecipeException;

    Recipe devolverRecetaPorId(Integer id, Integer idUsuario) throws RecipeDoesNotExistException;
    List<Recipe> devolverRecetasSinIngredienteOrdenadaPorAntiguedad(Integer idIngrediente, Integer idUsuario);
    List<Recipe> devolverRecetasSinIngredienteOrdenadaPorNombre(Integer idIngrediente, Integer idUsuario);

    List<Recipe> devolverRecetasPorBusquedaParcialNombre(String nombreReceta, Integer idUsuario);

    List<Recipe> devuelve3RecetasInicioApp(Integer idUsuario);

    List<Recipe> recetasPorNombreOrdenNombreUsuario(String nombreReceta, Integer idUsuario);

    List<Recipe> busquedaRecetaPorNombreOrdenadaPorAntiguedad(String nombreReceta, Integer idUsuario);

    List<Recipe> busquedaRecetasByParam(String nombreReceta, Integer idTipo, Integer idIngrediente, Integer idUsuario, Integer idUsuarioObligatorio);
    ReplaceRecipeResponseDto reemplazarReceta(Integer idReceta) throws RecipeDoesNotExistException;

    List<Recipe> devolverRecetasSinIngredienteOrdenadaPorNombreUser(Integer idIngrediente, Integer idUsuario);
}
