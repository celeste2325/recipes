package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.service.interfaces.StepService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pasos")
@AllArgsConstructor
public class StepController {
    private final StepService stepService;

    @GetMapping("{recipeID}")
    public ResponseEntity<?> getStepsByRecipeID(@PathVariable Integer recipeID) {
        return new ResponseEntity<>(this.stepService.getStepsByRecipe(recipeID), HttpStatus.OK);
    }


}
