package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.exception.ExistingIngredientException;
import com.distribuidas.recipe.model.entities.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientService {

    Ingredient salvarIngrediente(Ingredient newIngrediente) throws ExistingIngredientException;

    List<Ingredient> listarIngredientes();

    List<Ingredient> devolverIngredientesPorBusquedaParcial(String nombreParcialIngrediente);
}
