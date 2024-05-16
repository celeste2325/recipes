package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.exception.ExistingIngredientException;
import com.distribuidas.recipe.model.entities.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientService {

    Ingredient saveIngredient(Ingredient newIngredient) throws ExistingIngredientException;

    List<Ingredient> getIngredients();

    List<Ingredient> getIngredientsByPartialName(String partialNameIngredient);
}
