package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.exception.ExistingIngredientException;
import com.distribuidas.recipe.model.entities.Ingredient;
import com.distribuidas.recipe.repository.IngredientRepository;
import com.distribuidas.recipe.service.interfaces.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient saveIngredient(Ingredient newIngredient) throws ExistingIngredientException {
        Ingredient ingredientFound = this.ingredientRepository.findByName(newIngredient.getName());
        if (ingredientFound == null) {
            return this.ingredientRepository.save(newIngredient);
        } else {
            throw new ExistingIngredientException("the ingredient already exists");
        }

    }

    public List<Ingredient> getIngredients() {
        return this.ingredientRepository.findAll();
    }

    @Override
    public List<Ingredient> getIngredientsByPartialName(String partialNameIngredient) {
        if (!Objects.equals(partialNameIngredient, "")) {
            return this.ingredientRepository.findByNameLikeIgnoreCase(partialNameIngredient + "%");
        }
        return null;
    }
}
