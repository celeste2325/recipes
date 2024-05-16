package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.model.entities.Step;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StepService {
    List<Step> getStepsByRecipe(Integer recipeID);
}
